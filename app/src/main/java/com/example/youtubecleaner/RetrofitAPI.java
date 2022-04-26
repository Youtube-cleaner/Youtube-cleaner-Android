package com.example.youtubecleaner;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @FormUrlEncoded
    @POST("/execYC.php")

    Call<String> execYC(@Field("uri") String uri);
}
