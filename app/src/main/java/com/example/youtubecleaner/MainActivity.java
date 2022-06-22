package com.example.youtubecleaner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtUri;    // uri이 입력될 EditText
    private Button btnCheck;    // ResultActivity로 이동하기 위한 Button

    String strUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        edtUri = (EditText) findViewById(R.id.edtUri);
        btnCheck = (Button) findViewById(R.id.btnCheck);


        // 버튼 클릭
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUri.getText().toString().length() == 0) {    // uri 입력하지 않았을 때
                    Toast.makeText(getApplicationContext(), "동영상 주소를 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if(edtUri.getText().toString().length()<11){
                    Toast.makeText(getApplicationContext(), "올바른 주소를 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
                else {
                    // EditText에 입력된 주소를 String 형태로 저장하여 LoadingActivity로 넘겨줌
                    strUri = edtUri.getText().toString();

                    Log.d("activity", "MainActivity | intent : putExtra_"+strUri);
                    Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
                    intent.putExtra("strUri", strUri);
                    startActivity(intent);
                }
            }
        });
    }

}