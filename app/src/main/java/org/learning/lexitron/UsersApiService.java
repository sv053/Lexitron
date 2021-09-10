package org.learning.lexitron;

import org.learning.lexitron.model.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UsersApiService {

//    @GET("/users/{id}")
//    public Call<User> getPostWithID(@Path("id") int id);

    @GET("/users/findAll")
    public retrofit2.Call<List<User>> getAllUsers();

    @GET("/users/findByLogin")
    public retrofit2.Call<User> getUserWithLogin(@Query("login") String login);

//    @GET("/users")
//    public Call<List<User>> getUserById(@Query("userId") int id);

//    @POST("/users")
//    public Call<User> postData(@Body User data);

    @POST("/users")
    public retrofit2.Call<User> chooseUser(@Body String data);
}
