package com.example.youtubecleaner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {
    private RetrofitClient retrofitClient;
    private RetrofitAPI retrofitAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        // 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // MainActivity에서 넘겨준 uri을 받아옴
        Intent intent = getIntent();
        String strUri = intent.getStringExtra("strUri");
        Log.d("intent", "LoadingActivity | getExtra_" + strUri);

        String videoID = strUri.substring(strUri.length()-11, strUri.length());
        Log.d("Intent", "LoadingActivity | videoID = "+videoID);

        RetrofitResponse(videoID);

    }

    public void RetrofitResponse(String videoID) {
        String youtubeID = videoID;

        //RetrofitRequest retrofitRequest = new RetrofitRequest(youtubeID);

        retrofitClient = RetrofitClient.getInstance();
        retrofitAPI = RetrofitClient.getRetrofitInterface();

        // RetrofitRequest에 저장된 데이터와 함께 RetrofitAPI에서 정의한 execYC함수를 실행한 후 응답을 받음
        retrofitAPI.execYC(youtubeID).enqueue(new Callback<List<RetrofitResponse>>() {
            @Override
            public void onResponse(Call<List<RetrofitResponse>> call, Response<List<RetrofitResponse>> response) {
                Log.d("retrofit", "LoadingActivity | onResponse : Data fetch success");

                // 통신 성공
                if (response.isSuccessful() && response.body()!=null){
                    // response.body()를 result에 저장
                    // List<RetrofitResponse> result = response.body();

                    List<RetrofitResponse> items = response.body();
                    int listSize = items.size();
                    String[] resultUserID = new String[listSize];
                    String[] resultComment = new String[listSize];
                    float[] resultScore = new float[listSize];
                    int i=0;
                    for (RetrofitResponse item : items) {
                        resultUserID[i] = item.getUserID();
                        resultComment[i] = item.getComment();
                        resultScore[i] = item.getScore();
                        i++;
                    }

                    /*
                    // 받은 데이터 저장
                    String resultUserID = result.getUserID();
                    String resultComment = result.comment;
                    Float resultScore = result.score;

                     */

                    nextActivity(youtubeID, resultUserID, resultComment, resultScore);
                } else {
                    // HTTP 상태코드 3xx, 4xx
                    try{
                        String body = response.errorBody().string();
                        Log.d("retrofit", "LoadingActivity | onResponse isSuccessful == false : " + body);
                    }catch(IOException e){
                        e.printStackTrace();
                    }catch(NullPointerException e){
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("videoID", youtubeID);
                    startActivity(intent);
                }
            }

            // 인터넷 장애로 인한 통신 실패
            @Override
            public void onFailure(Call<List<RetrofitResponse>> call, Throwable t) {
                Log.d("retrofit","LoadingActivity | RetrofitResponse : onFailure");
                t.printStackTrace();
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("videoID", youtubeID);
                startActivity(intent);
            }
        });
    }

    // 뒤로가기 버튼을 눌러도 동작하지 않게 함.
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    public void nextActivity(String videoID, String[] id, String[] cmt, float[] score) {
        // 3초 뒤에 ResultActivity로 전환
        Log.d("activity", "LoadingActivity | ResultActivity로 전환");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, ResultActivity.class);
                intent.putExtra("videoID", videoID);
                intent.putExtra("userID", id);
                intent.putExtra("comment", cmt);
                intent.putExtra("score", score);

                startActivity(intent);
            }
        }, 3000);
    }
}
