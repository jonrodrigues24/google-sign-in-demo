package edu.cnm.deepdive.teamassignmentsandroid.service;

import android.content.Context;
import android.util.Log;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Task;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.User;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Fetches "Group" data from web service proxy.
 */
public class GroupRepository {

  private final Context context;
  private final GoogleSignInService signInService;
  private final WebServiceProxy webService;
  private final ExecutorService pool;

  /**
   * Passes context for google sign in.
   *
   * @param context is received for current process.
   */
  public GroupRepository(Context context) {
    this.context = context;
    webService = WebServiceProxy.getInstance();
    signInService = GoogleSignInService.getInstance();
    pool = Executors.newFixedThreadPool(4);
  }

  /**
   * Sets List of group, gets bearer token for authentication.
   *
   * @return groups if bear token is authenticated.
   */
  public Single<List<Group>> getGroups(User user) {
    return getGroups(false, user);
  }

  /**
   * Gets List of groups.
   *
   * @param ownedOnly are groups authenticated by user.
   * @return list of groups owned by user.
   */
  public Single<List<Group>> getGroups(boolean ownedOnly, User user) {
    Log.d(getClass().getSimpleName(), "userId = " + user.getId());
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((bearerToken) -> webService.getGroups(ownedOnly, bearerToken))
        .map((groups) -> groups
            .stream()
            .peek((group) -> {
              Log.d(getClass().getSimpleName(), "ownerId = " + group.getOwner().getId());

              group.setCurrentUserOwner(ownedOnly || group.getOwner().getId().equals(user.getId()));
            })
            .collect(Collectors.toList()));

  }

  public Single<Group> getGroup(long groupId, User user) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((bearerToken) -> webService.getGroup(groupId, bearerToken)
            .map((group) -> {
              group.setCurrentUserOwner(group.getOwner().getId().equals(user.getId()));
              return group;
            })
            .flatMap((group) -> webService.getMembers(groupId, bearerToken)
                .map((members) -> {
                  group.getUsers().addAll(members);
                  return group;
                }))
        );
  }

  /**
   * Saves single group to data base.
   *
   * @param group is sent to database
   * @return group and bearer token
   */
  public Completable saveGroup(Group group) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.from(pool))
        .flatMapCompletable((bearerToken) -> (group.getId() != 0)
            ? webService
            .renameGroup(group.getId(), group.getName(), bearerToken)
            .map((name) -> group)
            .flatMap((g) -> webService.getMembers(g.getId(), bearerToken))
            .flatMapCompletable((members) -> updateMembers(new HashSet<>(members), group, bearerToken))
            : webService
                .postGroup(group, bearerToken)
                .map((g) -> {
                  g.getUsers().clear();
                  g.getUsers().addAll(group.getUsers());
                  return g;
                })
                .flatMapCompletable((g) -> updateMembers(Collections.emptySet(), g, bearerToken))
        );


  }

  private Completable updateMembers(Set<User> previousMembers, Group group, String bearerToken) {
    Set<User> toRemove = previousMembers
        .stream()
        .filter((user) -> !group.getUsers().contains(user))
        .collect(Collectors.toSet());
    Set<User> toAdd = group.getUsers()
        .stream()
        .filter((user) -> !previousMembers.contains(user))
        .collect(Collectors.toSet());
    Log.d(getClass().getSimpleName(), toRemove.toString());
    Log.d(getClass().getSimpleName(), toAdd.toString());
    Completable removeTask = Observable
        .fromIterable(toRemove)
        .flatMapCompletable((user) -> webService
            .putMembership(false, user.getId(), group.getId(), bearerToken)
            .ignoreElement()
        );
    Completable addTask = Observable
        .fromIterable(toAdd)
        .flatMapCompletable((user) -> webService
            .putMembership(true, user.getId(), group.getId(), bearerToken)
            .ignoreElement()
        );
    return removeTask
        .concatWith(addTask);
  }

  public Completable deleteGroup(long groupId) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMapCompletable((bearerToken) ->
            webService.deleteGroup(groupId, bearerToken));
  }

  public Single<List<User>> getMembers(long groupId) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((bearerToken) -> webService.getMembers(groupId, bearerToken));
  }

  /**
   * Gets List of tasks
   *
   * @param groupId is retrieved from service proxy
   * @return list of tasks with id
   */
  public Single<List<Task>> getTasks(
      long groupId) { //TODO add logic to set flag in each task telling me weather current user is assigned, and owner of group
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((bearerToken) -> webService.getTasks(groupId, bearerToken));
  }

  public Single<Task> saveTask(long groupId, Task task) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((bearerToken) -> (task.getId() != null)
            ? webService.putTask(groupId, task.getId(), task, bearerToken)
            : webService.postTask(task, groupId, bearerToken)
        );
  }

  public Single<Task> getTask(long groupId, long taskId) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((bearerToken) -> webService.getTask(groupId, taskId, bearerToken));
  }

  public Completable deleteTask(long groupId, long taskId) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMapCompletable((bearerToken) ->
            webService.deleteTask(groupId, taskId, bearerToken));
  }
}
