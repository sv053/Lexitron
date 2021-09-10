package org.learning.lexitron;

import org.learning.lexitron.model.Text;
import org.learning.lexitron.model.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TextsApiService {

//    @GET("/users/{id}")
//    public Call<User> getPostWithID(@Path("id") int id);

    @GET("/texts/findAllTexts")
    public retrofit2.Call<List<Text>> getAllTexts();

    @GET("/texts/findByTitle")
    public retrofit2.Call<Text> getTextByTitle(@Query("title") String title);

//    @GET("/users")
//    public Call<List<User>> getUserById(@Query("userId") int id);

//    @POST("/users")
//    public Call<User> postData(@Body User data);

    @POST("/texts")
    public retrofit2.Call<User> chooseText(@Body String data);
}
