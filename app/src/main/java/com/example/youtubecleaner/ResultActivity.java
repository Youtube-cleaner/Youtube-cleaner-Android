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
import android.widget.ListView;
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
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity{

    ImageView ivThumbnail;
    Button btnSort;
    TextView tvYoutubeName, tvYoutubeTitle, tvUserID, tvComment, tvScore;
    private long backKeyPressedTime = 0;
    String youtubeURL="https://noembed.com/embed?url=https://www.youtube.com/watch?v=";
    ListView listView;
    ListViewAdapter adapter;

    ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>();
    String[] arrUserID, arrComment;
    Integer[] arrScore;

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

        Log.d("Intent", "ResultActivity | videoID = " +videoID);

        //Glide 라이브러리를 사용해서 유튜브 썸네일을 가져오는 작업
        ivThumbnail=(ImageView)findViewById(R.id.ivThumbnail);
        //String youtubeID = "MyOoYJPQK9w";   // 유튜브 영상의 고유 ID. 테스트용.
        //String thumbnail = "https://img.youtube.com/vi/"+youtubeID+"/"+"default.jpg";   // 유튜브 썸네일 이미지 주소
        String thumbnail = "https://img.youtube.com/vi/"+videoID+"/"+"default.jpg";   // 유튜브 썸네일 이미지 주소
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
                //jsonParsing(youtubeID);
                jsonParsing(videoID);
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

        // 리스트뷰에 데이터 추가
        adapter = new ListViewAdapter(itemList);

        listView = (ListView) findViewById(R.id.listview0);
        listView.setAdapter(adapter);

        DemoData dm = new DemoData();
        int numContent = dm.getNum();

        arrUserID = dm.getUserID();
        arrComment = dm.getComment();
        arrScore = dm.getScore();

        for(int i=0; i<numContent; i++){
            //Log.d("ResultActivity", "userID="+arrUserID[i]+"," + "comment="+arrComment[i]+", score="+arrScore[i]);
            adapter.addItem(arrUserID[i], arrComment[i], arrScore[i]);
        }


    }


    // youtube 동영상 이름과 채널명을 json 형식으로 받아오기
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


}