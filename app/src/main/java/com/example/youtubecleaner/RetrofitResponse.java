package com.example.youtubecleaner;

import com.google.gson.annotations.SerializedName;

public class RetrofitResponse {
    @SerializedName("userID")
    public String userID;

    @SerializedName("comment")
    public String comment;

    @SerializedName("score")
    public float score;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
