package com.example.youtubecleaner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {
    private static Retrofit retrofit;
    private static RetrofitAPI api;

    public static RetrofitAPI getRetrofit(){
        Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

        if(retrofit == null || api == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://52.79.100.62/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            api = retrofit.create(RetrofitAPI.class);

            return api;
        }
        return api;
    }
}
