package com.example.youtubecleaner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ResultActivity extends AppCompatActivity{

    ImageView ivThumbnail;
    Button btnSort;
    TextView tvYoutubeName, tvYoutubeTitle;
    private long backKeyPressedTime = 0;
    String youtubeURL="https://noembed.com/embed?url=https://www.youtube.com/watch?v=";

    Handler handler = new Handler();

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

        Log.d("activity", "ResultActivity | ResultActivity로 전환됨");

        tvYoutubeName = (TextView) findViewById(R.id.tvYoutubeName);
        tvYoutubeTitle = (TextView) findViewById(R.id.tvYoutubeTitle);

        Intent intent = getIntent();
        String videoID = intent.getStringExtra("videoID");
        String userID = intent.getStringExtra("userID");
        String comment = intent.getStringExtra("comment");
        float fScore = intent.getFloatExtra("score", 0);
        String score = Float.toString(fScore);


        //Glide 라이브러리를 사용해서 유튜브 썸네일을 가져오는 작업
        ivThumbnail=(ImageView)findViewById(R.id.ivThumbnail);
        //String youtubeId = "l9td5nMYAP8";   // 유튜브 영상의 고유 ID. 테스트용.
        String youtubeID = "MyOoYJPQK9w";   // 유튜브 영상의 고유 ID. 테스트용.
        String thumbnail = "https://img.youtube.com/vi/"+youtubeID+"/"+"default.jpg";   // 유튜브 썸네일 이미지 주소
        //String thumbnail = "https://img.youtube.com/vi/"+videoID+"/"+"default.jpg";   // 유튜브 썸네일 이미지 주소
        Glide.with(this)                 // Activity Context
                .load(thumbnail)                // 불러올 이미지
                .error(R.drawable.error)        // 이미지를 불러오다가 에러가 발생했을 때 보여줄 이미지 설정
                .fallback(R.drawable.error)     // 불러올 이미지가 null인 경우 보여줄 이미지 설정
                .into(ivThumbnail);             // 이미지를 보여줄 View 설정


        // JSONObject를 이용하여 유튜브 제목, 유튜브 채널 이름 가져오기
        // HttpURLConnection을 사용하기 때문에 스레드로 접근
        new Thread(new Runnable() {
            @Override
            public void run() {
                jsonParsing(youtubeID);
            }
        }).start();



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

        // 테스트용 코드
        TextView tvNickname = (TextView)findViewById(R.id.tvNickName2);
        TextView tvComment = (TextView)findViewById(R.id.tvContent2);
        TextView tvScore = (TextView)findViewById(R.id.tvPoint2);

        //tvNickname.setText(userID);
        //tvComment.setText(comment);
        //tvScore.setText(score);



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

    protected void jsonParsing(String youtubeID){
        String urlStr = youtubeURL+youtubeID;
        String jsonStr="";
        String youtubeName, youtubeTitle;
        StringBuilder output = new StringBuilder();

        try{
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn!=null){
                conn.setConnectTimeout(10000); // 연결 대기 시간
                conn.setRequestMethod("GET");
                conn.setDoInput(true);  // 객체의 입력이 가능하도록

                /*
                  응답코드가 HTTP_OK 인 경우 : 정상적인 응답이 온 것이므로 응답으로 들어온 스트림을 문자열로 반환하여 출력
                  요청한 주소의 페이지가 없는 경우 : HTTP_NOT_FOUND 코드가 반환.
                 */

                int resCode = conn.getResponseCode();

                // 입력 데이터를 받기 위한 Reader 객체 생성
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;

                while (true) {
                    line = reader.readLine();
                    if (line == null){
                        break;
                    }
                    output.append(line + "\n");
                }

                reader.close();
                conn.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("HttpConnection", "Error : "+e.toString());
        }
        jsonStr = output.toString();    // jsonStr에 json형식의 데이터 문자열로 변환되어 들어감

        try {
            JSONObject jObject = new JSONObject(jsonStr);
            youtubeTitle = jObject.getString("title");
            youtubeName = jObject.getString("author_name");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvYoutubeTitle.setText(youtubeTitle);
                    tvYoutubeName.setText(youtubeName);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
    public void setTv(final String str, int i){
        // int i : 1이면 title, 2면 name

        handler.post(new Runnable() {
            @Override
            public void run() {
                switch (i==1){
                    tvYoutubeTitle.setText(str);
                }
            }
        });
    }

     */


}

class Sub extends LinearLayout{
    public Sub(Context context){
        super(context);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ex_content, this, true);
        //inflater.inflate(R.layout.content, this, true);
    }
}
