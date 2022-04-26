package com.example.youtubecleaner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ResultActivity extends AppCompatActivity{

    ImageView ivThumbnail;
    Button btnSort;
    private long backKeyPressedTime = 0;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 누른 시간이 2초가 지났을 경우
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 마지막으로 뒤로가기 버튼을 누른 시간이 2초 이내일 경우 앱 종료
        if(System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finishAffinity();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        // 액션바 설정. 뒤로가기 버튼 생성
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  // 눌렀을때 발생하는 건 manifest에서
        actionBar.setTitle("");

        Intent intent = getIntent();
        String videoID = intent.getStringExtra("videoID");

        //Glide 라이브러리를 사용해서 유튜브 썸네일을 가져오는 작업
        ivThumbnail=(ImageView)findViewById(R.id.ivThumbnail);
        //String youtubeId = "l9td5nMYAP8";   // 유튜브 영상의 고유 ID. 테스트용.
        String thumbnail = "https://img.youtube.com/vi/"+videoID+"/"+"default.jpg";   // 유튜브 썸네일 이미지 주소
        Glide.with(this)                 // Activity Context
                .load(thumbnail)                // 불러올 이미지
                .error(R.drawable.error)        // 이미지를 불러오다가 에러가 발생했을 때 보여줄 이미지 설정
                .fallback(R.drawable.error)     // 불러올 이미지가 null인 경우 보여줄 이미지 설정
                .into(ivThumbnail);             // 이미지를 보여줄 View 설정

        // 버튼 누르면 오름차순 또는 내림차순으로 정렬
        btnSort = (Button)findViewById(R.id.btnSort);
        btnSort.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(btnSort.getText().toString()=="내림차순"){
                    btnSort.setText("오름차순");
                } else
                    btnSort.setText("내림차순");
            }
        });

        Sub nLayout = new Sub(getApplicationContext());
        LinearLayout con = (LinearLayout) findViewById(R.id.con);
        con.addView(nLayout);


        /*
        btnTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Sub nLayout = new Sub(getApplicationContext());
                LinearLayout con = (LinearLayout) findViewById(R.id.con);
                con.addView(nLayout);
            }
        });
        */


    }


}

class Sub extends LinearLayout{
    public Sub(Context context){
        super(context);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ex_content, this, true);
    }
}
