package com.kyh.musicservice0417;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startMusic, stopMusic;

    TelephonyManager telephonyManager;
    IntentFilter intentFilter;

    PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            super.onCallStateChanged(state, phoneNumber);
            Log.d("onCallStateChanged", "state " + state);
            Log.d("onCallStateChanged", "phoneNumber " + phoneNumber);

            if(state == TelephonyManager.CALL_STATE_RINGING) {
                MusicService.getContext().pauseMusic();
                Log.d("onCallStateChanged", "state CALL_STATE_RINGING");
            } else if(state == TelephonyManager.CALL_STATE_IDLE) {
                MusicService.getContext().resumeMusic();
                Log.d("onCallStateChanged", "state CALL_STATE_IDLE");
            } else if(state == TelephonyManager.CALL_STATE_OFFHOOK) {
                Log.d("onCallStateChanged", "state CALL_STATE_OFFHOOK");
            }
        }
    };;

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("MainActivity", "MainActivity onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("MainActivity", "MainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            MusicService.getContext().resumeMusic();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        Log.d("MainActivity", "MainActivity onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("MainActivity", "MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("MainActivity", "MainActivity onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            MusicService.getContext().pauseMusic();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        Log.d("MainActivity", "MainActivity onPause");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startMusic = findViewById(R.id.musicStart);
        stopMusic = findViewById(R.id.musicStop);

        Intent intent = new Intent(getApplicationContext(), MusicService.class);

        startMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        stopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

        intentFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }
}