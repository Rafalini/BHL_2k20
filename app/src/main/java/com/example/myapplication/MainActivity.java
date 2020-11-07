package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.models.DatabaseHelper;
import com.example.myapplication.models.FoodItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    ListView foodItemListView;
    FoodItemAdapter foodItemAdapter;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    protected void onStart () {
        super.onStart();
        Cursor data = dbHelper.getData();
        ArrayList<FoodItem> foodItemList = new ArrayList<>();
        while(data.moveToNext()) {
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(data.getString(2));
            } catch (ParseException e) {
                String name = data.getString(1);
                String dateStr = data.getString(2);
                e.printStackTrace();
            }
            foodItemList.add(new FoodItem(data.getString(1), date));
        }
        getFoodItems(foodItemList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.settings_item:
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

    public void AddData(FoodItem newEntry) {
        boolean insertData = dbHelper.addData(newEntry);
        if(insertData) {

        } else {

        }
    }



}