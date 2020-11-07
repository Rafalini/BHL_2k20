package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.Comparator;

public class DateSorter implements Comparator<FoodItem>
{
    public int compare(FoodItem f1, FoodItem f2)
    {
        return f1.getExpirationDate().compareTo(f2.getExpirationDate());
    }

    public static void mySort(ArrayList<FoodItem> x) {
        FoodItem temp;
        DateSorter d = new DateSorter();
        for (int i = 0; i < x.size() - 1; i++) {
            for (int j = i + 1; j < x.size(); j++) {
                if (d.compare(x.get(i), x.get(j)) > 0) {
                    temp = x.get(i);
                    x.set(i, x.get(j));
                    x.set(j, temp);

                }
            }
        }
    }
}

