package com.example.Rate.Retrofit;

import org.jetbrains.annotations.NotNull;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @POST("/register")
    @FormUrlEncoded
    @NotNull
    Call<ResponseBody> Register(@Field("id") @NotNull String id, @Field("password") @NotNull String password, @Field("hours") @NotNull Integer hours, @Field("minutes") @NotNull Integer minutes);

    @POST("/login")
    @FormUrlEncoded
    @NotNull
    Call<ResponseBody> Login(@Field("id") @NotNull String id, @Field("password") @NotNull String password);

    @GET("/set_rating")
    @NotNull
    Call<ResponseBody> setRating(@Query("id") @NotNull String id, @Query("what_you_do") @NotNull String s1, @Query("what_you_good") @NotNull String s2, @Query("what_you_bad") @NotNull String s3, @Query("year") @NotNull Integer year, @Query("month") @NotNull Integer month, @Query("day") @NotNull Integer days);
}
