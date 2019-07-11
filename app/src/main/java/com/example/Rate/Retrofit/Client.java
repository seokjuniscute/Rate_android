package com.example.Rate.Retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    public static API api;
    public static Client instance = null;
    public static String baseURL = "http://yuicrafts.xyz:16404";
    public static String id = null;

    static {
        instance = new Client();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient logger = new OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
        Retrofit retrofit = new retrofit2.Retrofit.Builder().baseUrl(baseURL).addConverterFactory((Converter.Factory) GsonConverterFactory.create()).client(logger).build();
        api = retrofit.create(API.class);
    }
}
