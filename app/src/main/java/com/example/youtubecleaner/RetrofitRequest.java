package com.example.youtubecleaner;

import com.google.gson.annotations.SerializedName;

public class RetrofitRequest {
    @SerializedName("youtubeID")
    public String youtubeID;

    public String getYoutubeID() {
        return youtubeID;
    }

    public void setYoutubeID(String youtubeID) {
        this.youtubeID = youtubeID;
    }

    public RetrofitRequest(String youtubeID){
        this.youtubeID = youtubeID;
    }
}
