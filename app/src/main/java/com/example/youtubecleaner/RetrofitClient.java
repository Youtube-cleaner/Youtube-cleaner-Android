package com.example.youtubecleaner;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private static RetrofitAPI retrofitAPI;

    private static String baseUri = "http://13.124.173.165/";

    private RetrofitClient() {

        // 로그를 보기 위한 Interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                                .addInterceptor(interceptor)
                                .build();

        // 문자열에 특수문자가 삽입되어 MalformedJsonException 에러가 생김
        // .setLenient()를 설정하면 엄격한 규칙을 적용하지 않고 유연하게 분석
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        // Retrofit 설정
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(baseUri)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(client) // 로그 기능 추가
                            .build();

        retrofitAPI = retrofit.create(RetrofitAPI.class);

    }

    public static RetrofitClient getInstance() {
        if (instance == null){
            instance = new RetrofitClient();
        }
        Log.d("retrofit", "RetrofitClient | getInstance()");
        return instance;
    }

    public static RetrofitAPI getRetrofitInterface() {
        Log.d("retrofit", "RetrofitClient | getRetrofitInterface()");
        return retrofitAPI;
    }
}
