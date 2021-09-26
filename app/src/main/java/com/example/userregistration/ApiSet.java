package com.example.userregistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiSet {

    @FormUrlEncoded
    @POST("create.php")
    Call<RegisterResponse> registerUser(
            @Field("username") String username,
            @Field("age") String age,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("password") String password,
            @Field("address") String address);

    @GET("read.php")
    Call<List<FetchUserResponse>> fetchUser();

    @FormUrlEncoded
    @POST("findUser.php")
    Call<RegisterResponse> findUser(@Field("id") String id);


    @FormUrlEncoded
    @POST("update.php")
    Call<RegisterResponse> updateUser(
            @Field("id") String id,
            @Field("username") String username,
            @Field("age") String age,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("password") String password,
            @Field("address") String address);


    @FormUrlEncoded
    @POST("delete.php")
    Call<RegisterResponse> delete(@Field("id") String id);

}
