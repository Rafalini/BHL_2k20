package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.models.FoodItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView foodItemRecyclerView;
    FoodItemAdapter foodItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<FoodItem> foodItemList = new ArrayList<FoodItem>();
        foodItemList.add(new FoodItem("Chicken", new Date(120,10,6)));
        foodItemList.add(new FoodItem("Cheese", new Date(120,10,6)));
        foodItemList.add(new FoodItem("Ham", new Date(120,10,6)));

        getFoodItems(foodItemList);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.settings_item:
                //setContentView(R.layout.activity_settings);
                new SettingsActivity();
                return true;
            case R.id.help_item:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getFoodItems(List<FoodItem> foodItemList) {
        foodItemRecyclerView = findViewById(R.id.food_items_recycler);
        foodItemAdapter = new FoodItemAdapter(this, foodItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        foodItemRecyclerView.setLayoutManager(layoutManager);
        foodItemRecyclerView.setAdapter(foodItemAdapter);
    }

}