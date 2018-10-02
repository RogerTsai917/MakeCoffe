package com.roger.makecoffee.objects.define;

import java.util.ArrayList;

public class MakeCoffeeTeaching {
    private String mCoffeeName;
    private int mCoffeeDrawableId;
    private ArrayList<MakeCoffeeStep> mMakeCoffeeStepsArrayList;

    public MakeCoffeeTeaching() {
        mMakeCoffeeStepsArrayList = new ArrayList<>();
    }

    public String getCoffeeName() {
        return mCoffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.mCoffeeName = coffeeName;
    }

    public int getCoffeeDrawableId() {
        return mCoffeeDrawableId;
    }

    public void setCoffeeDrawableId(int coffeeDrawableId) {
        this.mCoffeeDrawableId = coffeeDrawableId;
    }

    public ArrayList<MakeCoffeeStep> getMakeCoffeeStepsArrayList() {
        return mMakeCoffeeStepsArrayList;
    }


    public void addCoffeeStep(MakeCoffeeStep step) {
        mMakeCoffeeStepsArrayList.add(step);
    }
}
