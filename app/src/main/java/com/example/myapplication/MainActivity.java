package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

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
            foodItemList.add(new FoodItem(data.getString(0), data.getString(1), date));
        }
        getFoodItems(foodItemList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_item:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.help_item:
                startActivity(new Intent(this, HelpActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getFoodItems(ArrayList<FoodItem> foodItemList) {
        foodItemListView = findViewById(R.id.food_items_list);
        foodItemAdapter = new FoodItemAdapter(this, foodItemList);
        foodItemListView.setAdapter(foodItemAdapter);
        foodItemListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        FoodItem foodItem = (FoodItem) foodItemListView.getItemAtPosition(position);
                        dbHelper.deleteProduct(foodItem.getID());
                        finish();
                        startActivity(getIntent());
                        return true;
                    }
                });

                popupMenu.show();
                return true;
            }
        });
    }

    public void AddData(FoodItem newEntry) {
        boolean insertData = dbHelper.addData(newEntry);
        if(insertData) {

        } else {

        }
    }



}