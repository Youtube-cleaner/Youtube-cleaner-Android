package com.example.youtubecleaner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity{

    //LinearLayout llName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  // 눌렀을때 발생하는 건 manifest에서
        actionBar.setTitle("");

        Intent intent = getIntent();
        String strUri = intent.getStringExtra("strUri");
        
        //llName=(LinearLayout) findViewById(R.id.llName);
        //createTextView();
    }

    /*
    private void createTextView(){
        TextView newTv = new TextView(getApplicationContext());
        newTv.setText("원하는 텍스트");
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        param.bottomMargin=8;
        newTv.setLayoutParams(param);
        llName.addView(newTv);
    }

     */
}
