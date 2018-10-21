package com.roger.makecoffee.objects.define;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class LikedArticle {
    private String articleUid;
    @ServerTimestamp private Date addTime;

    public String getArticleUid() {
        return articleUid;
    }

    public void setArticleUid(String articleUid) {
        this.articleUid = articleUid;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
