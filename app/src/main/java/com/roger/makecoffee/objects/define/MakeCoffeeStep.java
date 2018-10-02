package com.roger.makecoffee.objects.define;

public class MakeCoffeeStep {
    /*
    stepType：
        prepare：準備材料，器具
        waterTemp：水溫
        timing：計時
        normal：其他步驟
        completed：完成
     */
    private String mStepType;

    private String mContent;

    private String mOtherMessage;

    public MakeCoffeeStep(String stepType, String content) {
        this.mStepType = stepType;
        this.mContent = content;
    }

    public MakeCoffeeStep(String stepType, String content, String otherMessage) {
        this.mStepType = stepType;
        this.mContent = content;
        this.mOtherMessage = otherMessage;
    }

    public String getStepType() {
        return mStepType;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public String getOtherMessage() {
        return mOtherMessage;
    }

    public void setOtherMessage(String message) {
        mOtherMessage = message;
    }
}
