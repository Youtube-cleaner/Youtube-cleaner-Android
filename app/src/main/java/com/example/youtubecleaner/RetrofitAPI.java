package com.example.youtubecleaner;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {

    //@FormUrlEncoded
    //@POST("/posts")
    //@POST("/api_youtubecleaner")
    @POST("/index/execYC.php")

    // RetrofitRequest에 정의해준 데이터들을 서버 Body에 보낸 후 RetrofitResponse로 데이터를 받겠다는 의미
    Call<RetrofitResponse> execYC(@Body RetrofitRequest retrofitRequest);

    //    //Call<String> execYC(@Query("uri") String uri);
    //
    //    // execYC = 안드로이드에서 사용될 함수
    // @Query("") : php 파일에서 입력받을 변수 이름.
    // String : 안드로이드에서 넘겨줄 변수.
}
