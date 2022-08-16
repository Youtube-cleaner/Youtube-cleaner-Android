package com.example.youtubecleaner;

import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Text;

public class RetrofitResponse {
    @SerializedName("userID")
    private String userID;
    @SerializedName("comment")
    private String comment;
    @SerializedName("score")
    private Float score;

    public final String getUserID() {
        return userID;
    }

    public String getComment() {
        return comment;
    }

    public float getScore(){
        float fScore = Float.valueOf(score);
        return score;
    }
}
