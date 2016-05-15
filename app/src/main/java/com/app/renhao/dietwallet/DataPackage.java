package com.app.renhao.dietwallet;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2016-04-28.
 */
public class DataPackage extends RealmObject {


    @PrimaryKey
    private int sno = 0;

    private String date = "";
    private int money = 0;
    private int calorie = 0;
    private String foodname = "";

    public DataPackage() {

    }


    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getSno() {
        return sno;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }
}
