package com.example.youtubecleaner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

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

        // 4초 뒤에 ResultActivity로 전환
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, ResultActivity.class);
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
