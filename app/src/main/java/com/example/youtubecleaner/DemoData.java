package com.example.youtubecleaner;

import android.util.Log;

public class DemoData {
    private String[] arrUserID = {"유나", "가든", "성은", "JH", "연어", "제주감귤", "쌍둥이", "윰", "빈", "요요", "발리", "쯔밍", "hee"};
    private String[] arrComment = {"역시 재미있어~ 바뀐 헤어스타일 너무 이뻐요ㅎㅎ", "구독하고 열심히 정주행 해야겠다 ㅎㅎ", "핑계 지리네 ㅋㅋㅋ 님 마인드부터 틀리셨어요 ㅋㅋ 진짜 답답...",
            "아너무 예쁜 사람이다\n당신은 소중해요\n항상 웃는일만 있으시길", "너무 귀엽고 사랑스러운 분...ㅎㅎ", "언니 진짜 매력넘쳐요 ㅠㅠ", "이분 앞으로 행복했으면 좋겠당ㅎㅎ", "머리 좀 감으면 안됨? ;;",
            "쩝쩝소리 자제좀... 듣기 거북함", "정주행 중인데 전부 변명들로 징징징", "이 영상 뭔가 중독적... 5번은 본 듯", "영상 하나하나가 소중해 ㅜㅜㅜ", "머리하신거 너무 예뻐요!! 긴머리가 잘어울리시는것 같아용 ㅎㅎ"};
    private Integer[] arrScore = {10, 1, 79, 1, 10, 1, 15, 68, 74, 76, 28, 8, 10};

    int numComment = arrUserID.length;

    public String[] getUserID(){
        return arrUserID;
    }

    public String[] getComment(){
        return arrComment;
    }

    public Integer[] getScore(){
        return arrScore;
    }

    public int getNum(){
        return numComment;
    }

}
