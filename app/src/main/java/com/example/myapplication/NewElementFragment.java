package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.models.DatabaseHelper;
import com.example.myapplication.models.FoodItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewElementFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private String foodName;
    private String date;
    private DialogFragment calendarDialogFragment;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        calendarDialogFragment = new DatePickerFragment();
        return inflater.inflate(R.layout.new_element_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.selectDateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarDialogFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        view.findViewById(R.id.confirmButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameText = getView().findViewById(R.id.ProductNameInput);
                String foodName = nameText.getText().toString();

                TextView dateText = getView().findViewById(R.id.dateDisplayText);
                String foodExpirationDate = dateText.getText().toString().substring(19);
                try {
                    Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(foodExpirationDate);
                    FoodItem newEntry = new FoodItem(foodName, expirationDate);
                    dbHelper.addData(newEntry);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                NavHostFragment.findNavController(NewElementFragment.this)
                        .navigate(R.id.action_NewElementFragment_to_MainFragment);
            }
        });

        view.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewElementFragment.this)
                        .navigate(R.id.action_NewElementFragment_to_MainFragment);
            }
        });
    }

}
