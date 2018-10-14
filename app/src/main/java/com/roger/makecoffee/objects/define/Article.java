package com.roger.makecoffee.objects.define;


import com.google.firebase.firestore.ServerTimestamp;

import java.util.ArrayList;
import java.util.Date;

public class Article {
    private String articleUid;
    private String title;
    private String description;
    private String imageUrl;
    private Author author;
    private ArrayList<ArticleStep> articleStepArrayList;
    @ServerTimestamp private Date createdTime;

    public Article() {
        articleStepArrayList = new ArrayList<>();
    }

    public String getArticleUid() {
        return articleUid;
    }

    public void setArticleUid(String articleUid) {
        this.articleUid = articleUid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ArrayList<ArticleStep> getArticleStepArrayList() {
        return articleStepArrayList;
    }

    public void setArticleStepArrayList(ArrayList<ArticleStep> articleStepArrayList) {
        this.articleStepArrayList = articleStepArrayList;
    }

    public void addArticleStepArrayList(ArticleStep articleStep) {
        articleStepArrayList.add(articleStep);
    }

    public int getArticleStepArrayListSize() {
        return articleStepArrayList.size();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
