package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.models.Database;
import com.example.myapplication.models.FoodItem;
import com.example.myapplication.models.Parser;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    ListView foodItemListView;
    FoodItemAdapter foodItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    protected void onStart () {
        super.onStart();
        ArrayList<FoodItem> foodItemList = null;
        try {
            foodItemList = (ArrayList<FoodItem>) Parser.getFoodItems(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        getFoodItems(foodItemList);
        try {
            Database.saveFoodItem(getApplicationContext(), foodItemList.get(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.settings_item:
                //setContentView(R.layout.activity_settings);
                //new SettingsActivity();
                return true;
            case R.id.help_item:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getFoodItems(ArrayList<FoodItem> foodItemList) {
        foodItemListView = findViewById(R.id.food_items_list);
        foodItemAdapter = new FoodItemAdapter(this, foodItemList);
        foodItemListView.setAdapter(foodItemAdapter);
    }

}