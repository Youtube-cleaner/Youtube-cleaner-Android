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

import com.google.android.material.slider.RangeSlider;

public class MainActivity extends AppCompatActivity {

    private EditText edtUri;
    private RangeSlider rangeSlider;
    private Button btnCheck;
    private Float range1, range2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        edtUri = (EditText) findViewById(R.id.edtUri);
        rangeSlider = (RangeSlider) findViewById(R.id.rangeSlider);
        btnCheck = (Button) findViewById(R.id.btnCheck);

        rangeSlider.setValues(30f,60f);
        rangeSlider.addOnSliderTouchListener(rangeSliderListener);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    private final RangeSlider.OnSliderTouchListener rangeSliderListener = new RangeSlider.OnSliderTouchListener() {
        @Override
        public void onStartTrackingTouch(@NonNull RangeSlider slider) {
            // 바가 시작하면 동작
        }

        @Override
        public void onStopTrackingTouch(@NonNull RangeSlider slider) {
            // 사용자가 바에서 손을 뗐을 때
            range1 = slider.getValues().get(0);
            range2 = slider.getValues().get(1);
            Log.i("rangeSlider", "range1 : "+range1);
            Log.i("rangeSlider", "range2 : "+range2);
        }
    };
}