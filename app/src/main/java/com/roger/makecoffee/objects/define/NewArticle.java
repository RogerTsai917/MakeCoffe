package com.roger.makecoffee.objects.define;

import com.google.firebase.firestore.ServerTimestamp;
import com.roger.makecoffee.user.UserManager;

import java.util.Date;

public class NewArticle {
    private String articleUid;
    private String title;
    private String content;
    private String imageUrl;
    private Author author;
    private String coffeeBean;
    private String coffeeBeanRoastLevel;
    private String coffeeBeanGrind;
    private String coffeeBeanVolume;
    private String waterTemp;
    private String waterVolume;
    private String coffeeTool;
    private String time;
    private String coffeeVolume;
    private String additive;
    private int flavorBody;
    private int flavorAcidity;
    private int flavorBitter;
    private int flavorSweet;
    private int flavorAroma;
    private String supplement;
    @ServerTimestamp private Date createdTime;

    public NewArticle() {
        initialNewArticle();
    }

    private void initialNewArticle() {
        title = "";
        content = "";
        imageUrl = "";

        Author author = new Author();
        author.setName(UserManager.getInstance().getUserName());
        author.setEmail(UserManager.getInstance().getUserEmail());
        author.setImage(UserManager.getInstance().getUserPhotoUrl());
        author.setUid(UserManager.getInstance().getUserUid());
        setAuthor(author);

        coffeeBean = "";
        coffeeBeanRoastLevel = "";
        coffeeBeanGrind = "";
        coffeeBeanVolume = "";
        waterTemp = "";
        waterVolume = "";
        coffeeTool = "";
        time = "";
        coffeeVolume = "";
        additive = "";

        flavorBody = 0;
        flavorAcidity = 0;
        flavorBitter = 0;
        flavorSweet = 0;
        flavorAroma = 0;

        supplement = "";
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getCoffeeBean() {
        return coffeeBean;
    }

    public void setCoffeeBean(String coffeeBean) {
        this.coffeeBean = coffeeBean;
    }

    public String getCoffeeBeanRoastLevel() {
        return coffeeBeanRoastLevel;
    }

    public void setCoffeeBeanRoastLevel(String coffeeBeanRoastLevel) {
        this.coffeeBeanRoastLevel = coffeeBeanRoastLevel;
    }

    public String getCoffeeBeanGrind() {
        return coffeeBeanGrind;
    }

    public void setCoffeeBeanGrind(String coffeeBeanGrind) {
        this.coffeeBeanGrind = coffeeBeanGrind;
    }

    public String getCoffeeBeanVolume() {
        return coffeeBeanVolume;
    }

    public void setCoffeeBeanVolume(String coffeeBeanVolume) {
        this.coffeeBeanVolume = coffeeBeanVolume;
    }

    public String getWaterTemp() {
        return waterTemp;
    }

    public void setWaterTemp(String waterTemp) {
        this.waterTemp = waterTemp;
    }

    public String getWaterVolume() {
        return waterVolume;
    }

    public void setWaterVolume(String waterVolume) {
        this.waterVolume = waterVolume;
    }

    public String getCoffeeTool() {
        return coffeeTool;
    }

    public void setCoffeeTool(String coffeeTool) {
        this.coffeeTool = coffeeTool;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCoffeeVolume() {
        return coffeeVolume;
    }

    public void setCoffeeVolume(String coffeeVolume) {
        this.coffeeVolume = coffeeVolume;
    }

    public String getAdditive() {
        return additive;
    }

    public void setAdditive(String additive) {
        this.additive = additive;
    }

    public int getFlavorBody() {
        return flavorBody;
    }

    public void setFlavorBody(int flavorBody) {
        this.flavorBody = flavorBody;
    }

    public int getFlavorAcidity() {
        return flavorAcidity;
    }

    public void setFlavorAcidity(int flavorAcidity) {
        this.flavorAcidity = flavorAcidity;
    }

    public int getFlavorBitter() {
        return flavorBitter;
    }

    public void setFlavorBitter(int flavorBitter) {
        this.flavorBitter = flavorBitter;
    }

    public int getFlavorSweet() {
        return flavorSweet;
    }

    public void setFlavorSweet(int flavorSweet) {
        this.flavorSweet = flavorSweet;
    }

    public int getFlavorAroma() {
        return flavorAroma;
    }

    public void setFlavorAroma(int flavorAroma) {
        this.flavorAroma = flavorAroma;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

}
