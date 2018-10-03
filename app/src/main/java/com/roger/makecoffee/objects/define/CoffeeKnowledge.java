package com.roger.makecoffee.objects.define;

public class CoffeeKnowledge {
    private String name;
    private int drawableId;
    private String content;

    public CoffeeKnowledge(String name,int drawableId ,String content) {
        this.name = name;
        this.drawableId = drawableId;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return drawableId;
    }

    public void setImage(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
