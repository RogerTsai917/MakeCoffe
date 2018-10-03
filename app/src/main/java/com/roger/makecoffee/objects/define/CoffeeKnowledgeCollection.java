package com.roger.makecoffee.objects.define;

import java.util.ArrayList;

public class CoffeeKnowledgeCollection {
    private String name;
    private int drawableId;
    private ArrayList<CoffeeKnowledge> coffeeKnowledgeArrayList;

    public CoffeeKnowledgeCollection() {
        coffeeKnowledgeArrayList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public ArrayList<CoffeeKnowledge> getCoffeeKnowledgeArrayList() {
        return coffeeKnowledgeArrayList;
    }

    public void addCoffeeKnowledge(CoffeeKnowledge knowledge) {
        coffeeKnowledgeArrayList.add(knowledge);
    }
}
