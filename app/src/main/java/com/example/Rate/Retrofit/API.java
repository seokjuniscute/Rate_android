package com.example.Rate.Retrofit;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

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

    @POST("/set_rating")
    @NotNull
    Call<ResponseBody> setRating(@Field("id") @NotNull String id,
                                 @Field("what_you_do") @NotNull String s1,
                                 @Field("what_you_good") @NotNull String s2,
                                 @Field("what_you_bad") @NotNull String s3,
                                 @Field("date") @NotNull Date date);
}
