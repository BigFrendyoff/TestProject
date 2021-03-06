package com.example.mysicsync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button starter = findViewById(R.id.begin_btn);
        starter.setOnClickListener(v -> {
            Intent toController = new Intent(this, ControllerActivity.class);
            startActivity(toController);
        });
    }

}