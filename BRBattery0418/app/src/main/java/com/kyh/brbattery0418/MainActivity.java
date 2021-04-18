package com.kyh.brbattery0418;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView ivBattery;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBattery = findViewById(R.id.ivBattery);

        info = findViewById(R.id.info);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

                info.setText("현재 충전량 : " + remain + "%\n");

                if(remain >= 90) {
                    ivBattery.setImageResource(R.drawable.battery_100);
                } else if (remain >= 70) {
                    ivBattery.setImageResource(R.drawable.battery_80);
                } else if (remain >= 50) {
                    ivBattery.setImageResource(R.drawable.battery_60);
                } else if (remain >= 10) {
                    ivBattery.setImageResource(R.drawable.battery_20);
                } else {
                    ivBattery.setImageResource(R.drawable.battery_0);
                }

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);

                switch (plug) {
                    case 0:
                        info.append("전원 연결 : 안됨\n");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        info.append("전원 연결 : 어뎁터 연결\n");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        info.append("전원 연결 : USB 연결\n");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                        info.append("전원 연결 : 무선 충전 연결\n");
                        break;
                }

                // 배터리 상태
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);

                switch (status) {
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        Toast.makeText(context, "현재 충전중입니다", Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        Toast.makeText(context, "충전기가 연결되지 않았습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        Toast.makeText(context, "충전이 완료되었습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        Toast.makeText(context, "충전기가 분리되었습니다", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(context, "ErrorCode : CurrentStatusUnknown", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };
}