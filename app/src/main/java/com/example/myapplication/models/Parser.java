package com.example.myapplication.models;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Parser {

    private static final String FILE_NAME = "database.json";

    public static List<FoodItem> getFoodItems(Context context)
    {
        String jsonFileString = Database.getJsonFromAssets(context, FILE_NAME);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<FoodItem>>() { }.getType();

        List<FoodItem> foodItemList = gson.fromJson(jsonFileString, listUserType);
        return foodItemList;
    }

    public static String getJSONObject(FoodItem f)
    {
        Gson gson = new Gson();
        return gson.toJson(f);
    }
}
