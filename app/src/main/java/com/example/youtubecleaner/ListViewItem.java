package com.example.youtubecleaner;

public class ListViewItem {
    private String itemUserID, itemComment;
    private int itemScore;

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

    public int getItemScore() {
        return itemScore;
    }

    public void setItemScore(int itemScore) {
        this.itemScore = itemScore;
    }
}
