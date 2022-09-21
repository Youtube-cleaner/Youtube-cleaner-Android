package com.example.youtubecleaner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("execYC.php?")

    Call<List<RetrofitResponse>> execYC(@Query("youtubeID") String youtubeID);

    // RetrofitRequest에 정의해준 데이터들을 서버 Body에 보낸 후 RetrofitResponse로 데이터를 받겠다는 의미

    //Call<RetrofitResponse> execYC(@Body RetrofitRequest retrofitRequest);
    // 위 코드는 post 방식으로 보낼 때 사용
}