package com.example.exwidget0313;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button btnStart, btnEnd;
    RadioGroup showType;
    RadioButton rbtnCal, rbtnTime;
    CalendarView calendarView;
    TimePicker timePicker;
    TextView pvYear, pvMonth, pvDate, pvHour, pvMin;

    int selectYear, selectMonth, selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("시간 예약 설정 앱 1.0v");

        chronometer = findViewById(R.id.chronometer);

        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);

        showType = findViewById(R.id.showType);
        rbtnCal = findViewById(R.id.rbtnCal);
        rbtnTime = findViewById(R.id.rbtnTime);

        calendarView = findViewById(R.id.calendarView);
        timePicker = findViewById(R.id.timePicker);

        pvYear = findViewById(R.id.pvYear);
        pvMonth = findViewById(R.id.pvMonth);
        pvDate = findViewById(R.id.pvDate);
        pvHour = findViewById(R.id.pvHour);
        pvMin = findViewById(R.id.pvMin);

        calendarView.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);

        rbtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.INVISIBLE);
            }
        });

        rbtnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });

        // chronometer start
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.BLUE);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chronometer start
                chronometer.stop();
                chronometer.setTextColor(Color.RED);

                // date, time data
                pvYear.setText(selectYear + "");
                pvMonth.setText(selectMonth + "");
                pvDate.setText(selectDate + "");
                pvHour.setText(timePicker.getCurrentHour() + "");
                pvMin.setText(timePicker.getCurrentMinute() + "");
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectYear = year;
                selectMonth = month+1;
                selectDate = dayOfMonth;

                Log.d("calendarViewData",selectYear+"/"+selectMonth+"/"+selectDate);
            }
        });
    }
}