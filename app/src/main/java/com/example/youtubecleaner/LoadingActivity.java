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

public class LoadingActivity extends AppCompatActivity {

    public RetrofitAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        // 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        api = RetrofitInit.getRetrofit();

        // MainActivity에서 넘겨준 uri을 받아옴
        Intent intent = getIntent();
        String strUri = intent.getStringExtra("strUri");
        String videoID = strUri.substring(strUri.length()-11, strUri.length());


        /*
        Call<String> execYC = api.execYC("strUri");
        execYC.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    Log.e("onSuccess", response.body());




                    JsonObject items = response.body();
                    for(JsonObject item : items) {

                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
        */

        // 4초 뒤에 ResultActivity로 전환
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, ResultActivity.class);
                intent.putExtra("videoID", videoID);
                startActivity(intent);
            }
        }, 4000);
    }

    // 뒤로가기 버튼을 눌러도 동작하지 않게 함.
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
