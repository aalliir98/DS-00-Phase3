package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
TextView step;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        step = findViewById(R.id.result2);
        getSupportActionBar().setTitle("Steps");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        step.setText(getIntent().getStringExtra(MainActivity1.EXTRA_TEXT));
    }
}