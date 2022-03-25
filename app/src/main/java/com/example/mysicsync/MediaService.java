package com.example.mysicsync;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MediaService extends Service {
    MediaPlayer localMediaPlayer;
    IBinder binder = new LocalBinder();


    public class LocalBinder extends Binder{
        MediaService getService() {
            return MediaService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        localMediaPlayer = MediaPlayer.create(this, R.raw.sample);
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        localMediaPlayer = MediaPlayer.create(this, R.raw.sample);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        localMediaPlayer.stop();
    }

    public void starter(){
        localMediaPlayer.start();
    }
    public void pauser(){
        localMediaPlayer.pause();
    }
}
