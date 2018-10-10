package com.roger.makecoffee.objects.define;

public class ArticleStep {
    private String content;
    private String photoUrl;

    public ArticleStep() {

    }

    public ArticleStep(String content, String photoUrl) {
        this.content = content;
        this.photoUrl = photoUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
