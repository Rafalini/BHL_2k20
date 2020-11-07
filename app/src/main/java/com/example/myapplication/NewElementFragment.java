package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

                if (foodName.isEmpty() || dateText.getText().toString().isEmpty()){

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setCancelable(true);
                    builder.setTitle("Brak danych");
                    builder.setMessage("Nazwa lub data ważności produktu nie zostały podane ");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) { }
                    });
                    builder.show();
                } else {
                    String foodExpirationDate = dateText.getText().toString().substring(19);
                    try {
                        Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(foodExpirationDate);
                        if(expirationDate.before(Calendar.getInstance().getTime())){

                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setCancelable(true);
                            builder.setTitle("Zła data ważności");
                            builder.setMessage("Data ważności nie może być wcześniejsza od dzisiejszego dnia.");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) { }
                            });
                            builder.show();

                        } else {
                            FoodItem newEntry = new FoodItem(null, foodName, expirationDate);
                            dbHelper.addData(newEntry);
                            startActivity(new Intent(getContext(), MainActivity.class));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        view.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
    }

}
