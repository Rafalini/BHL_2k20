package com.example.myapplication.models;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Parser {

    public static List<FoodItem> getFoodItems(Context context) throws IOException {
        String jsonFileString = Database.getJsonFromAssets(context);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<FoodItem>>() { }.getType();

        List<FoodItem> foodItemList = gson.fromJson(jsonFileString, listUserType);
        return foodItemList;
    }

    public static JSONObject getJSONObject(FoodItem f) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", f.name);
        jsonObject.put("expirationDate", f.expirationDate);
        return jsonObject;
    }
}
