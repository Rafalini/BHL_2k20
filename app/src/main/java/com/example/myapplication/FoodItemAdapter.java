package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapplication.models.FoodItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FoodItemAdapter extends ArrayAdapter<FoodItem> {

    private final Context context;

    public FoodItemAdapter(Context context, ArrayList<FoodItem> values) {
        super(context, -1, values);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemViewHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.food_recycler_items, null);
            holder = new ItemViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.food_item_name);
            holder.dateTextView = (TextView) convertView.findViewById(R.id.food_item_date);
            convertView.setTag(holder);
        } else {
            holder = (ItemViewHolder) convertView.getTag();
        }


        FoodItem foodItem = getItem(position);
        holder.nameTextView.setText(foodItem.getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(foodItem.getExpirationDate());
        String dateText = "Expiration date: " + date;
        holder.dateTextView.setText(dateText);

        return convertView;
    }

    static class ItemViewHolder {
        private TextView nameTextView;
        private TextView dateTextView;
    }


}
