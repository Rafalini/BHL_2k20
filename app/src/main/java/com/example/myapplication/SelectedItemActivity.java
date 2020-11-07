package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.Date;

public class SelectedItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SelectedItemFragment())
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String data = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String name = data.substring(0, data.indexOf("\n"));
        String date = data.substring(data.indexOf("\n")+1);
        getSupportActionBar().setTitle(name);
        TextView textView = findViewById(R.id.expirationalDateValue);
        textView.setText(date);

        WebView myWebView = (WebView) findViewById(R.id.CookbookPage);
        String url = "https://www.kwestiasmaku.com/szukaj?search_api_views_fulltext=" + name;
        myWebView.loadUrl(url);
    }
}