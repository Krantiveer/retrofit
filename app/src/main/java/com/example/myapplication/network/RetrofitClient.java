package com.example.myapplication.network;


import com.example.myapplication.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String API_EXTENSION = "v1/";
    private static final String API_USER_NAME = "admin";
    private static final String API_PASSWORD = "1234";
    private static Retrofit retrofitAuth, retrofit;


    public static Retrofit getRetrofitInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new
                OkHttpClient.Builder()
                .readTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS);
     /*   client.setConnectTimeout(30, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(30, TimeUnit.SECONDS);
     */
        client.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .build();
            return chain.proceed(request);
        });
        client.addInterceptor(new BasicAuthInterceptor(API_USER_NAME, API_PASSWORD));

        //to enable logs
        client.addInterceptor(interceptor).build();
          /*      .addInterceptor(new BasicAuthInterceptor(API_USER_NAME, API_PASSWORD))
                .addInterceptor(interceptor).build();*/

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitInstanceWithV1() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader("publisherid", Config.Publisher_id).build();
            return chain.proceed(request);
        });
        client.addInterceptor(new BasicAuthInterceptor(API_USER_NAME, API_PASSWORD));
        client.addInterceptor(interceptor).build();
          /*      .addInterceptor(new BasicAuthInterceptor(API_USER_NAME, API_PASSWORD))
                .addInterceptor(interceptor).build();*/

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitInstanceAuth() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retrofitAuth == null) {
            retrofitAuth = new Retrofit.Builder()
                    .baseUrl(Config.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitAuth;
    }

}
