package edu.cnm.deepdive.teamassignmentsandroid.service;

import androidx.room.Delete;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.teamassignmentsandroid.BuildConfig;
import edu.cnm.deepdive.teamassignmentsandroid.model.Group;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.Task;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.User;
import io.reactivex.Single;
import java.util.Date;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebServiceProxy {

  @GET("users/me")
  Single<User> getProfile(@Header("Authorization") String bearerToken);

  @POST("groups")
  Single<Group> postGroup(@Body Group group, @Header("Authorization") String bearerToken);

  @PUT("groups/{groupId}/members/{userId}")
  Single<Boolean> putMembership(@Body boolean inGroup, @Path("userId") long userId,
      @Path("groupId") long groupId, @Header("Authorization") String bearerToken);

  @GET("groups/{groupId}/members/{userId}")
  Single<Boolean> getMembership(@Path("groupId") long groupId, @Path("groupId") long userId,
      @Header("Authorization") String bearerToken);

  @GET("groups/{id}")
  Single<Group> getGroup(@Path("id") long id, @Header("Authorization") String bearerToken);


  @PUT("groups/{id}")
  Single<String> renameGroup(@Path("id") long id, @Body String name, @Header("Authorization") String bearerToken);


  @GET("me/{me}")
  Single<User> getUser(@Path("me") String bearerToken); //TODO follow up with Nick


  @PUT("me/{id}")
  Single<String> renameUser(@Path("id") long id, @Body String name, @Header("Authorization")String bearerToken);


  //todo does adding body to all of these make sense
  @POST("tasks")
  Single<Task> postTask(@Body Task task,@Body User user,@Body Group group,@Body Date date, String bearerToken);



  static WebServiceProxy getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final WebServiceProxy INSTANCE;


    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel((BuildConfig.DEBUG) ? Level.BODY : Level.NONE);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();

      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(WebServiceProxy.class);
    }
  }

}
