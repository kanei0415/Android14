package com.kyh.thread0417;

import android.content.Context;
import android.os.Message;

public class WorkThread extends Thread{
    MainActivity context;

    public WorkThread(Context context) {
        this.context = (MainActivity)context;
    }

    @Override
    public void run() {
        for(int i=0 ; i<100 && context.isRunning(); i++) {
            Message msg = new Message();

            msg.what = 1000;
            msg.arg1 = i;

            context.getMyHandler().sendMessage(msg);

            // Message Send
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
