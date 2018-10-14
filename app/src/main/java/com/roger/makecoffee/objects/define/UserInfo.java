package com.roger.makecoffee.objects.define;

import java.util.ArrayList;

public class UserInfo {
    private String userUid;
    private String name;
    private String email;
    private String image;
    private ArrayList<String> postedArticlesList;
    private ArrayList<String> likedArticlesList;

    public UserInfo() {
        postedArticlesList = new ArrayList<>();
        likedArticlesList = new ArrayList<>();
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getPostedArticlesList() {
        return postedArticlesList;
    }

    public void setPostedArticlesList(ArrayList<String> postedArticlesList) {
        this.postedArticlesList = postedArticlesList;
    }

    public ArrayList<String> getLikedArticlesList() {
        return likedArticlesList;
    }

    public void setLikedArticlesList(ArrayList<String> likedArticlesList) {
        this.likedArticlesList = likedArticlesList;
    }
}
