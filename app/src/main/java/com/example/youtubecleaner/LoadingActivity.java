package com.example.youtubecleaner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        String videoID = strUri.substring(strUri.length()-11, strUri.length());

        Log.d("activity", "LoadingActivity 실행 뒤 getIntent");

        RetrofitResponse(videoID);

    }

    public void RetrofitResponse(String videoID) {
        String youtubeID = videoID;

        RetrofitRequest retrofitRequest = new RetrofitRequest(youtubeID);

        retrofitClient = RetrofitClient.getInstance();
        retrofitAPI = RetrofitClient.getRetrofitInterface();

        // RetrofitRequest에 저장된 데이터와 함께 RetrofitAPI에서 정의한 execYC함수를 실행한 후 응답을 받음
        retrofitAPI.execYC(retrofitRequest).enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                Log.d("retrofit", "Data fetch success");

                // 통신 성공
                if (response.isSuccessful() && response.body()!=null){
                    // response.body()를 result에 저장
                    RetrofitResponse result = response.body();

                    // 받은 데이터 저장
                    String resultUserID = result.getUserID();
                    String resultComment = result.getComment();
                    float resultScore = result.getScore();

                    nextActivity(youtubeID);
                }
            }

            // 통신 실패
            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                Log.d("retrofit","onFailure");
                nextActivity(youtubeID);
            }
        });
    }

    // 뒤로가기 버튼을 눌러도 동작하지 않게 함.
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    public void nextActivity(String videoID) {
        // 3초 뒤에 ResultActivity로 전환
        Log.d("activity", "LoadingActivity>ResultActivity");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, ResultActivity.class);
                intent.putExtra("videoID", videoID);
                startActivity(intent);
            }
        }, 3000);
    }
}
