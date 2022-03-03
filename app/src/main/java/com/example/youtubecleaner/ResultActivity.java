package com.example.youtubecleaner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity{

    Button btnBlock1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        // 액션바 설정. 뒤로가기 버튼 생성
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  // 눌렀을때 발생하는 건 manifest에서
        actionBar.setTitle("");


        //실험용. 버튼 누르면 Sub 클래스가 실행되면서 뷰가 동적 생성됨
        btnBlock1 = (Button) findViewById(R.id.btnBlock1);
        btnBlock1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
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
