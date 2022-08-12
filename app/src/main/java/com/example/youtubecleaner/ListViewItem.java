package com.example.youtubecleaner;

public class ListViewItem {
    private String itemUserID, itemComment;
    private Float itemScore;

    public String getItemUserID() {
        return itemUserID;
    }

    public void setItemUserID(String itemUserID) {
        this.itemUserID = itemUserID;
    }

    public String getItemComment() {
        return itemComment;
    }

    public void setItemComment(String itemComment) {
        this.itemComment = itemComment;
    }

    public Float getItemScore() {
        return itemScore;
    }

    public void setItemScore(Float itemScore) {
        this.itemScore = itemScore;
    }
}
