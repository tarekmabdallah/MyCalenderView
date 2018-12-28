package com.gmail.tarekmabdallah91.mycalenderview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.calendar_layout);
        int widthPx = getScreenWidthPx();
        int paddingVal = widthPx/21;
        int paddingVal2 = (int) (widthPx/16.5);
        int paddingVal3 = widthPx/17;

        String weekDays= "M,T,W,T,F,S,S";
        ArrayList<String> weekDaysList = new ArrayList<>(Arrays.asList(weekDays.split(",")));
        int dayNumber = 1, newMonthDayNumber = 1; ;
        // set days row in calendar view
        View calendarDaysRow = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.calendar_row, null, false);
        LinearLayout linearLayoutDaysRow = calendarDaysRow.findViewById(R.id.calendar_row);
        for (int i = 1; i <7 ; i++) {
            View dayItem = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.day_layout, null, false);
            TextView dayTV = dayItem.findViewById(R.id.day_tv);
            dayTV.setText(weekDaysList.get(i));
            dayTV.setPadding(paddingVal3,paddingVal,paddingVal3,paddingVal);
            linearLayoutDaysRow.addView(dayTV);
        }
        linearLayout.addView(linearLayoutDaysRow);

        // set month days in calendar view
        for (int j = 0; j < 6; j++) {
            View calendarRow = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.calendar_row, null, false);
            LinearLayout linearLayoutRow = calendarRow.findViewById(R.id.calendar_row);
            for (int i = 1; i <7 ; i++) {
                View dayItem = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                        .inflate(R.layout.day_layout, null, false);
                final TextView dayTV = dayItem.findViewById(R.id.day_tv);
                if (dayNumber < 10) {
                    dayTV.setText(String.valueOf(dayNumber++));
                    dayTV.setPadding(paddingVal2,paddingVal,paddingVal2,paddingVal);
                }else if (dayNumber > 31){
                    dayTV.setText(String.valueOf(newMonthDayNumber++));
                    dayTV.setPadding(paddingVal2,paddingVal,paddingVal2,paddingVal);
                    dayTV.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }else {
                    dayTV.setText(String.valueOf(dayNumber++));
                    dayTV.setPadding(paddingVal,paddingVal,paddingVal,paddingVal);
                }
                dayTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = dayTV.getText().toString();
                        Toast.makeText(getBaseContext(),text,Toast.LENGTH_SHORT).show();
                    }
                });
                linearLayoutRow.addView(dayTV);
            }
            linearLayout.addView(linearLayoutRow);
        }
    }

    private int getScreenWidthPx (){
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }
}
