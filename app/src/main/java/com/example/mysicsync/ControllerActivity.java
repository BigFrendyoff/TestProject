package com.example.mysicsync;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ControllerActivity extends AppCompatActivity {

    MediaService mPlayer;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_controller);
        FloatingActionButton playButton = findViewById(R.id.playButton);
        FloatingActionButton pauseButton = findViewById(R.id.pauseButton);
        Intent musicServiceActivation = new Intent(this, MediaService.class);

        bindService(musicServiceActivation, connected, BIND_AUTO_CREATE);
        playButton.setOnClickListener(v -> {
            mPlayer.starter();
            playButton.setEnabled(false);
            playButton.setVisibility(View.INVISIBLE);
            pauseButton.setEnabled(true);
            pauseButton.setVisibility(View.VISIBLE);
        });
        pauseButton.setOnClickListener(v -> {
            mPlayer.pauser();
            pauseButton.setEnabled(false);
            pauseButton.setVisibility(View.INVISIBLE);
            playButton.setEnabled(true);
            playButton.setVisibility(View.VISIBLE);
        });

    }
    private ServiceConnection connected = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MediaService.LocalBinder binder = (MediaService.LocalBinder) service;
            mPlayer = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };
}
