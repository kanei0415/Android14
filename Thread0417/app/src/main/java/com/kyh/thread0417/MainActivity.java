package com.kyh.thread0417;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView percent;

    public TextView getPercent() {
        return percent;
    }

    private Button start, stop;
    private ProgressBar progressBar;

    public ProgressBar getProgressBar() {
        return  progressBar;
    }

    private WorkThread workThread;
    private boolean running = false;

    public boolean isRunning() {
        return running;
    }

    MyHandler myHandler;

    public MyHandler getMyHandler() {
        return myHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        percent = findViewById(R.id.percent);

        start = findViewById(R.id.thStart);
        stop = findViewById(R.id.thStop);

        progressBar = findViewById(R.id.progressBar);

        myHandler = new MyHandler(this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workThread = new WorkThread(MainActivity.this);
                workThread.setDaemon(true); // main Thread와 work Thread를 같이 종료

                workThread.start(); running = true;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });
    }
}