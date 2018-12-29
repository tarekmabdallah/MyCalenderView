package com.gmail.tarekmabdallah91.mycalenderview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int INVALID = -1;
    private static final int ONE = 1;
    private static final int ZERO = 0;
    private static final int SEVEN = 7;
    private int monthDaysCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar calendar = Calendar.getInstance();
        final int MONTH = calendar.get(Calendar.MONTH) + ONE;
        final int YEAR = calendar.get(Calendar.YEAR);
        final int CURRENT_DAY = calendar.get(Calendar.DAY_OF_MONTH);
        setMonthDaysCount(MONTH, YEAR);
        LinearLayout linearLayout = findViewById(R.id.calendar_layout);
        final String WEEK_DAYS = "MON,TUE,WED,THU,FRI,SAT,SUN";
        final ArrayList<String> WEEK_DAYS_LIST = new ArrayList<>(Arrays.asList(WEEK_DAYS.split(",")));
        final String FIRST_DAY_OF_MONTH = getFirstDayInMonth(CURRENT_DAY, MONTH, YEAR);
        final int INDEX_FIRST_DAY = getIndexOfFirstDay(FIRST_DAY_OF_MONTH, WEEK_DAYS_LIST);

        int dayNumber = ONE, newMonthDayNumber = ONE, d = ZERO;
        final String EMPTY_STRING = "";
        // set days row in calendar view
        View calendarDaysRow = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.calendar_row, null, false);
        LinearLayout linearLayoutDaysRow = calendarDaysRow.findViewById(R.id.calendar_row);
        for (String dayName : WEEK_DAYS_LIST) {
            View dayItem = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.day_layout, null, false);
            TextView dayTV = dayItem.findViewById(R.id.day_tv);
            dayTV.setText(dayName.substring(ZERO, ONE));
            linearLayoutDaysRow.addView(dayTV);
        }
        linearLayout.addView(linearLayoutDaysRow);

        // set month days in calendar view
        for (int j = ONE; j < SEVEN; j++) {
            View calendarRow = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.calendar_row, null, false);
            LinearLayout linearLayoutRow = calendarRow.findViewById(R.id.calendar_row);
            for (int i = ONE; i <= SEVEN; i++) {
                View dayItem = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                        .inflate(R.layout.day_layout, null, false);
                final TextView dayTV = dayItem.findViewById(R.id.day_tv);
                if (d++ < INDEX_FIRST_DAY) {
                    dayTV.setText(EMPTY_STRING);
                }else {
                    if (dayNumber <= monthDaysCount) {
                        dayTV.setText(String.valueOf(dayNumber++));
                    } else {
                        dayTV.setText(String.valueOf(newMonthDayNumber++));
                        dayTV.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    }
                    dayTV.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String text = dayTV.getText().toString();
                            Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                linearLayoutRow.addView(dayTV);
            }
            linearLayout.addView(linearLayoutRow);
        }
    }

    private String getFirstDayInMonth(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.MONTH, month - ONE);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_MONTH, ONE);
        Date firstDayOfMonth = cal.getTime();
        final DateFormat sdf = new SimpleDateFormat("EEEEEEEE", Locale.US);
        return sdf.format(firstDayOfMonth);
    }

    private int getIndexOfFirstDay(String dayName, List<String> days) {
        for (int i = ZERO; i < days.size(); i++)
            if (dayName.toLowerCase().equals(days.get(i).toLowerCase()))
                return i;
        return INVALID;
    }


    public void setMonthDaysCount(int month, int year) {
//        YearMonth yearMonthObject = YearMonth.of(year, month);
//        this.monthDaysCount = yearMonthObject.lengthOfMonth();
        // Create a calendar object and set year and month
        Calendar mycal = new GregorianCalendar(year, month, ONE);
        // Get the number of days in that month
        this.monthDaysCount = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}

