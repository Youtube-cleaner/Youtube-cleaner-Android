package com.example.youtubecleaner;

import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Text;

public class RetrofitResponse {
    @SerializedName("userID")
    public String userID;
    @SerializedName("comment")
    public String comment;
    @SerializedName("score")
    public Float score;
}
