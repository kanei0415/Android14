package com.kyh.musicservice0417;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {
    MediaPlayer mp;
    int position;

    static MusicService context;

    public static MusicService getContext() {
        return context;
    }

    public MusicService() {
        context = this;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MusicService", "MusicService OnCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MusicService", "MusicService onStartCommand");

        startMusic();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mp.stop();

        Log.d("MusicService", "MusicService onDestroy");
    }

    public void startMusic() {
        mp = MediaPlayer.create(this, R.raw.my_piano1);
        mp.setLooping(true);

        mp.start();
    }

    public void pauseMusic() {
        position = mp.getCurrentPosition();

        mp.pause();
    }

    public void resumeMusic() {
        mp.start();
        mp.seekTo(position);
    }
}