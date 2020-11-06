package com.example.myapplication.models;
import com.google.gson.Gson;

import org.json.*;

public class Parser {
    public static FoodItem getFoodItem(String s)
    {
        Gson gson = new Gson();

        return  gson.fromJson(s, FoodItem.class);
    }

    public static String getJSONObject(FoodItem f)
    {
        Gson gson = new Gson();
        return gson.toJson(f);
    }
}
