package com.example.youtubecleaner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ResultActivity extends AppCompatActivity{

    Button btnTest;
    ImageView ivThumbnail, ivResultDefault;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        // 액션바 설정. 뒤로가기 버튼 생성
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  // 눌렀을때 발생하는 건 manifest에서
        actionBar.setTitle("");

        //Glide 라이브러리를 사용해서 유튜브 썸네일을 가져오는 작업
        ivThumbnail=(ImageView)findViewById(R.id.ivThumbnail);
        ivResultDefault=(ImageView)findViewById(R.id.ivResultDefault);
        String youtubeId = "l9td5nMYAP8";   // 유튜브 영상의 고유 ID. 테스트용.
        String thumbnail = "https://img.youtube.com/vi/"+youtubeId+"/"+"default.jpg";   // 유튜브 썸네일 이미지 주소
        Glide.with(this)                 // Activity Context
                .load(thumbnail)                // 불러올 이미지
                .error(R.drawable.error)        // 이미지를 불러오다가 에러가 발생했을 때 보여줄 이미지 설정
                .fallback(R.drawable.error)     // 불러올 이미지가 null인 경우 보여줄 이미지 설정
                .into(ivThumbnail);             // 이미지를 보여줄 View 설정


        //실험용. 버튼 누르면 Sub 클래스가 실행되면서 뷰가 동적 생성됨
        btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ivResultDefault.setVisibility(View.GONE);
                Sub nLayout = new Sub(getApplicationContext());
                LinearLayout con = (LinearLayout) findViewById(R.id.con);
                con.addView(nLayout);
            }
        });

    }
}

class Sub extends LinearLayout{
    public Sub(Context context){
        super(context);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pra, this, true);
    }
}
