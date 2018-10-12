package com.roger.makecoffee.objects;

import android.util.Log;

import com.roger.makecoffee.R;
import com.roger.makecoffee.objects.define.MakeCoffeeStep;
import com.roger.makecoffee.objects.define.MakeCoffeeTeaching;
import com.roger.makecoffee.utils.Constants;

import java.util.ArrayList;

public class MakeCoffeeData {
    private static MakeCoffeeData mMakeCoffeeData;
    private ArrayList<MakeCoffeeTeaching> mMakeCoffeeTeachingsArrayList;

    private MakeCoffeeData() {
        mMakeCoffeeTeachingsArrayList = new ArrayList<>();

        // fake data
        makeFakeData();
    }

    public static synchronized MakeCoffeeData getInstance() {
        if (mMakeCoffeeData == null) {
            mMakeCoffeeData = new MakeCoffeeData();
        }
        return mMakeCoffeeData;
    }

    public ArrayList<MakeCoffeeTeaching> getMakeCoffeeTeachingsArrayList() {
        return mMakeCoffeeTeachingsArrayList;
    }

    public int getMakeCoffeeDataSize() {
        return mMakeCoffeeTeachingsArrayList.size();
    }

    public void addMakeCoffeeTeaching(MakeCoffeeTeaching teaching) {
        mMakeCoffeeTeachingsArrayList.add(teaching);
    }

    private void makeFakeData() {
        makeEspresso();
        makeAmericano();
        makeLatte();
        makeCappuccino();

    }

    private void makeEspresso() {
        MakeCoffeeTeaching teaching = new MakeCoffeeTeaching();
        teaching.setCoffeeName("Espresso");
        teaching.setCoffeeDrawableId(R.drawable.coffee_espresso);
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.PREPARE, "1. 16g 研磨咖啡豆\n2. 義式咖啡機"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.NORMAL, "1. 用手輕敲濾器把手側邊，讓咖啡平坦鋪勻。\n2. 用填壓器垂直壓粉，注意壓粉接觸面不得傾斜。"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.WATER_TEMP, "1. 裝上濾器把手前，讓水流掉約 2秒鐘。\n 2. 水溫需要 85 ~ 95ºC"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.TIMING, "萃取目標是 24 ~ 27秒內萃取出 30 ~32g 的咖啡液。", "27"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.COMPLETED, "完成一杯義式濃縮咖啡！"));

        addMakeCoffeeTeaching(teaching);
    }

    private void makeAmericano() {
        MakeCoffeeTeaching teaching = new MakeCoffeeTeaching();
        teaching.setCoffeeName("Americano");
        teaching.setCoffeeDrawableId(R.drawable.coffee_americano);
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.PREPARE, "1. 16g 研磨咖啡豆\n2. 義式咖啡機"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.NORMAL, "1. 用手輕敲濾器把手側邊，讓咖啡平坦鋪勻。\n2.用填壓器垂直壓粉，注意壓粉接觸面不得傾斜。"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.WATER_TEMP, "1. 裝上濾器把手前，讓水流掉約 2秒鐘。\n 水溫需要 85 ~ 95ºC"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.TIMING, "萃取目標是 24 ~ 27秒內萃取出 30 ~32g 的咖啡液。", "27"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.COMPLETED, "完成一杯義式濃縮咖啡！"));

        addMakeCoffeeTeaching(teaching);
    }

    private void makeLatte() {
        MakeCoffeeTeaching teaching = new MakeCoffeeTeaching();
        teaching.setCoffeeName("Latte");
        teaching.setCoffeeDrawableId(R.drawable.coffee_latte);
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.PREPARE, "1. 16g 研磨咖啡豆\n2. 義式咖啡機"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.NORMAL, "1. 用手輕敲濾器把手側邊，讓咖啡平坦鋪勻。\n2.用填壓器垂直壓粉，注意壓粉接觸面不得傾斜。"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.WATER_TEMP, "1. 裝上濾器把手前，讓水流掉約 2秒鐘。\n 水溫需要 85 ~ 95ºC"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.TIMING, "萃取目標是 24 ~ 27秒內萃取出 30 ~32g 的咖啡液。", "27"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.COMPLETED, "完成一杯義式濃縮咖啡！"));

        addMakeCoffeeTeaching(teaching);
    }

    private void makeCappuccino() {
        MakeCoffeeTeaching teaching = new MakeCoffeeTeaching();
        teaching.setCoffeeName("Cappuccino");
        teaching.setCoffeeDrawableId(R.drawable.coffee_cappuccino);
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.PREPARE, "1. 16g 研磨咖啡豆\n2. 義式咖啡機"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.NORMAL, "1. 用手輕敲濾器把手側邊，讓咖啡平坦鋪勻。\n2.用填壓器垂直壓粉，注意壓粉接觸面不得傾斜。"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.WATER_TEMP, "1. 裝上濾器把手前，讓水流掉約 2秒鐘。\n 水溫需要 85 ~ 95ºC"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.TIMING, "萃取目標是 24 ~ 27秒內萃取出 30 ~32g 的咖啡液。", "27"));
        teaching.addCoffeeStep(new MakeCoffeeStep(Constants.COMPLETED, "完成一杯義式濃縮咖啡！"));

        addMakeCoffeeTeaching(teaching);
    }

}
