package com.kyh.cpcalllog0418;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    Button getCallLog;
    EditText edCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCallLog = findViewById(R.id.getCallLog);
        edCall = findViewById(R.id.edCall);

        getCallLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edCall.setText(getCallInfo());
            }
        });
    }

    public void myCheckPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CALL_LOG}
            , MODE_PRIVATE);
        }
    }

    public String getCallInfo() {
        Cursor cursor = null;

        String[] callSet = new String[] {
          CallLog.Calls.DATE, CallLog.Calls.TYPE, CallLog.Calls.NUMBER, CallLog.Calls.DURATION
        };

        try {
            cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, callSet
                    , null, null, null);
        } catch (SecurityException e) {
            int per = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);

            new AlertDialog.Builder(MainActivity2.this)
                    .setTitle("CallLog Permission Setting")
                    .setMessage("프로그램을 실행하기 위해서는 CallLog 접근 권한 확득이 필요합니다.")
                    .setPositiveButton("권한 설정", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.READ_CALL_LOG}
                            ,MODE_PRIVATE);
                        }
                    })
                    .setNegativeButton("권한 취소", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity2.this, "프로그램을 실행하기 위해서는 CallLog 접근 권한 확득이 필요합니다.", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        }

        if(cursor == null || !cursor.moveToNext()) {
            return "통화 기록이 존재하지 않습니다.";
        }

        StringBuffer callBuffer = new StringBuffer();
        callBuffer.append("\n[Date] : [Delimiter] : [telephoneNumber] : [callTime]\n\n");

        cursor.moveToFirst();
        do {
            long callDate = cursor.getLong(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = dateFormat.format(new Date(callDate));
            callBuffer.append(dateStr + ":");

            if(cursor.getInt(1) == CallLog.Calls.INCOMING_TYPE) {
                callBuffer.append("착신 : ");
            } else {
                callBuffer.append("발신 : ");
            }

            callBuffer.append(cursor.getString(2) + ":");
            callBuffer.append(cursor.getString(3) + "초\n");
        } while (cursor.moveToNext());

        cursor.close();
        return callBuffer.toString();
    }
}