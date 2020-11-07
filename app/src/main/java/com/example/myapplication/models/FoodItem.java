package com.example.myapplication.models;

import java.util.Date;

public class FoodItem {
    String ID;
    String name;
    Date expirationDate;

    public FoodItem(String ID, String name, Date expirationDate) {
        this.ID = ID;
        this.name = name;
        this.expirationDate = expirationDate;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
