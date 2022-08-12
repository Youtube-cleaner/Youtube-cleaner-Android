package com.example.youtubecleaner;

import android.util.Log;

public class DemoData {
    private String[] arrUserID = {"유나", "가든", "성은", "JH", "연어", "제주감귤", "쌍둥이", "윰", "빈", "요요", "발리", "쯔밍", "hee"};
    private String[] arrComment = {"역시 재미있어~ 다음 컨텐츠도 기대된다 ㅋㅋ", "구독하고 열심히 정주행 해야겠다 ㅎㅎ", "사람 비꼬면서 하는 개그 재밌나 ㅜㅜ 진짜 예의없다",
            "오랜만에 이렇게 웃어보네요\n너무 재미있어요\n항상 웃는 일로 가득했으면~", "아진짜 너무 귀엽다 ㅋㅋㅋㅋㅋㅋ", "아 저 매력 어쩌냐고 ㅠㅠㅋㅋㅋㅋ", "아 진짜 레전드다 ㅋㅋㅋㅋㅋㅋㅋㅋ", "으 더러워... 저게 뭐가 재밌누",
            "으... 듣기 거북함....", "이번 편은 별로 재미 없는듯? 기대이하네", "이 영상 뭔가 중독적... 5번은 본 듯", "영상 하나하나가 소중해 ㅜㅜㅜ", "레전드 개웃김 ㅋㅋㅋㅋㅋㅋ 쿵짝 맞는거봐 ㄹㅇㅋㅋㅋㅋㅋ"};
    private Integer[] arrScore = {10, 1, 72, 1, 10, 1, 15, 86, 74, 66, 28, 8, 10};

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
