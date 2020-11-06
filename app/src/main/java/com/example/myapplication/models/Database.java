package com.example.myapplication.models;

import android.content.Context;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class Database {

    private static final String FILE_NAME = "database.json";

    public static String getJsonFromAssets(Context context) throws IOException {
        File file = new File(context.getFilesDir(),FILE_NAME);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();// This response will have Json Format String
        String response = stringBuilder.toString();

        return response;
    }

    public static boolean saveFoodItem(Context context, FoodItem foodItem) throws JSONException {
        String FoodItemString = Parser.getJSONObject(foodItem).toString();
        File file = new File(context.getFilesDir(), FILE_NAME);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(FoodItemString);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
