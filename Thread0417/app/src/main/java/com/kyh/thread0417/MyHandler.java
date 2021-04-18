package com.kyh.thread0417;


import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public class MyHandler extends Handler {
    MainActivity context;

    public MyHandler (Context context) {
        this.context = (MainActivity)context;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);

        if (msg.what == 1000) {
            // progress bar handling
            context.getProgressBar().setProgress(msg.arg1);

            context.getPercent().setText((msg.arg1+1) + "%");
        }
    }
}
