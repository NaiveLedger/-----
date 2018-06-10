package com.example.opaque.GAME.Bean;

/**
 id integer
 "name text,"+
 "attribute text"+
 "changeNum int"+
 "moneyReward int"+
 "content text)";
 * Created by Opaque on 2018/4/27.
 */

public class MissionBean {

    private String name;
    private int needWit;
    private int needPower;
    private int needCorporiety;
    private int needSpeed;
    private double needMoney;
    private int needLuck;
    private String whatAttrReward;
    private int  rewardNum;
    private int needMonth;
    private String needTime;
    private String content;
    private boolean isDone;
    private boolean isHasBeenDone;

    public int getNeedWit() {
        return needWit;
    }

    public void setNeedWit(int needWit) {
        this.needWit = needWit;
    }

    public int getNeedPower() {
        return needPower;
    }

    public void setNeedPower(int needPower) {
        this.needPower = needPower;
    }

    public int getNeedCorporiety() {
        return needCorporiety;
    }

    public void setNeedCorporiety(int needCorporiety) {
        this.needCorporiety = needCorporiety;
    }

    public int getNeedSpeed() {
        return needSpeed;
    }

    public void setNeedSpeed(int needSpeed) {
        this.needSpeed = needSpeed;
    }

    public double getNeedMoney() {
        return needMoney;
    }

    public void setNeedMoney(double needMoney) {
        this.needMoney = needMoney;
    }

    public int getNeedLuck() {
        return needLuck;
    }

    public void setNeedLuck(int needLuck) {
        this.needLuck = needLuck;
    }

    public String getWhatAttrReward() {
        return whatAttrReward;
    }

    public void setWhatAttrReward(String whatAttrReward) {
        this.whatAttrReward = whatAttrReward;
    }

    public int getRewardNum() {
        return rewardNum;
    }

    public void setRewardNum(int rewardNum) {
        this.rewardNum = rewardNum;
    }

    public int getNeedMonth() {
        return needMonth;
    }

    public void setNeedMonth(int needMonth) {
        this.needMonth = needMonth;
    }

    public String getNeedTime() {
        return needTime;
    }

    public void setNeedTime(String needTime) {
        this.needTime = needTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isHasBeenDone() {
        return isHasBeenDone;
    }

    public void setHasBeenDone(boolean hasBeenDone) {
        isHasBeenDone = hasBeenDone;
    }

    public MissionBean(String name, int needWit, int needPower,
                       int needCorporiety, int needSpeed, double needMoney,
                       int needLuck, String whatAttrReward, int rewardNum,
                       int needMonth, String needTime, String content) {
        this.name = name;
        this.needWit = needWit;
        this.needPower = needPower;
        this.needCorporiety = needCorporiety;
        this.needSpeed = needSpeed;
        this.needMoney = needMoney;
        this.needLuck = needLuck;
        this.whatAttrReward = whatAttrReward;
        this.rewardNum = rewardNum;
        this.needMonth = needMonth;
        this.needTime = needTime;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
