package com.example.opaque.GAME.Bean;

import java.util.ArrayList;

/**
 * Created by Opaque on 2018/5/31.
 */

public class EventBean {
    private String name;
    private String attribute;
    private int changeNum;
    private int moneyReward;
    private String content;

    public EventBean(String name, String attribute, int changeNum, int moneyReward, String content) {
        this.name = name;
        this.attribute = attribute;
        this.changeNum = changeNum;
        this.moneyReward = moneyReward;
        this.content = content;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(int changeNum) {
        this.changeNum = changeNum;
    }

    public int getMoneyReward() {
        return moneyReward;
    }

    public void setMoneyReward(int moneyReward) {
        this.moneyReward = moneyReward;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
