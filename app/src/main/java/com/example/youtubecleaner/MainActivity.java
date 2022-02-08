package com.example.youtubecleaner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.slider.RangeSlider;

public class MainActivity extends AppCompatActivity {

    private EditText edtUri;
    private Button btnCheck;

    String strUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        edtUri = (EditText) findViewById(R.id.edtUri);
        btnCheck = (Button) findViewById(R.id.btnCheck);



        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUri.getText().toString().length() == 0) {    // uri 입력하지 않았을 때
                    Toast.makeText(getApplicationContext(), "동영상 주소를 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    strUri = edtUri.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("strUri", strUri);
                    startActivity(intent);
                }
            }
        });
    }

}