package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.FoodItem;

import java.text.SimpleDateFormat;
import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {

    private Context context;
    private List<FoodItem> foodList;

    public FoodItemAdapter(Context context, List<FoodItem> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_recycler_items, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        holder.foodItemName.setText(foodList.get(position).getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(foodList.get(position).getExpirationDate());
        String dateText = "Expiration date: " + date;
        holder.foodItemDate.setText(dateText);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {

        TextView foodItemName;
        TextView foodItemDate;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);

            foodItemName = itemView.findViewById(R.id.food_item_name);
            foodItemDate = itemView.findViewById(R.id.food_item_date);
        }
    }

}
