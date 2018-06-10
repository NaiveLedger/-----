package com.example.opaque.GAME.Bean;

/**
 * Created by Opaque on 2018/4/27.
 */

public class PlayerBean {
    //七个属性
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private int life;
    private int power;
    private int wit;
    private int luck;
    private int speed;
    private int corporeity;
    private double money;


    public PlayerBean(int id, String name, int life, int power, int wit, int luck, int speed, int corporeity, double money) {
        this.id = id;
        this.name = name;
        this.life = life;
        this.power = power;
        this.wit = wit;
        this.luck = luck;
        this.speed = speed;
        this.corporeity = corporeity;
        this.money = money;
    }

    public PlayerBean(String name, int wit, int life, int power, int luck, int speed, int corporeity, double money) {
        this.name = name;
        this.corporeity = corporeity;
        this.life = life;
        this.power = power;
        this.luck = luck;
        this.speed = speed;
        this.wit = wit;
        this.money = money;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWit() {
        return wit;
    }

    public void setWit(int wit) {
        this.wit = wit;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return this.life;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getLuck() {
        return this.luck;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setCorporeity(int corporeity) {
        this.corporeity = corporeity;
    }

    public int getCorporeity() {
        return this.corporeity;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return this.money;
    }
}
