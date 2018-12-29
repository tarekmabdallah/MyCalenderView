/*
 * Copyright 2018 tarekmabdallah91@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gmail.tarekmabdallah91.simplecalendarview;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.content.ContextCompat.getColor;
import static com.gmail.tarekmabdallah91.simplecalendarview.MonthCalenderPresenter.ONE;
import static com.gmail.tarekmabdallah91.simplecalendarview.MonthCalenderPresenter.SEVEN;
import static com.gmail.tarekmabdallah91.simplecalendarview.MonthCalenderPresenter.ZERO;


public final class MonthCalenderFragment extends Fragment {

    private LinearLayout calendarLayout;
    private TextView calendarLabel;
    private MonthCalender monthCalender;
    private MonthCalenderPresenter presenter;
    private Context context;

    public static MonthCalenderFragment getInstance() {
        return new MonthCalenderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_month_calender, container, false);
        context = getContext();
        calendarLabel = rootView.findViewById(R.id.calendar_label);
        calendarLayout = rootView.findViewById(R.id.calendar_layout);
        calendarLayout.setBackgroundColor(getColor(context, monthCalender.getResBackgroundColorIdCalendar()));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = MonthCalenderPresenter.getInstance();
        setCalendarLabel();
        setUI();
    }

    @SuppressLint("InflateParams")
    private void setUI() {
        List<Integer> offDays = monthCalender.getOffDays();
        LayoutInflater layoutInflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        int dayNumber = ONE, newMonthDayNumber = ONE, d = ZERO;
        final String EMPTY_STRING = "";
        // set days row in calendar view
        View calendarDaysRow = layoutInflater.inflate(R.layout.calendar_row, null, false);
        LinearLayout linearLayoutDaysRow = calendarDaysRow.findViewById(R.id.calendar_row);
        for (String dayName : presenter.getWEEK_DAYS_LIST()) {
            View dayItem = layoutInflater.inflate(R.layout.day_layout, null, false);
            TextView dayTV = dayItem.findViewById(R.id.day_tv);
            dayTV.setText(dayName.substring(ZERO, ONE));
            dayTV.setTextColor(getColor(context, monthCalender.getResTextColorIdDays()));
            linearLayoutDaysRow.addView(dayTV);
        }
        linearLayoutDaysRow.setBackgroundColor(getColor(context, monthCalender.getResBackgroundColorIdDays()));
        calendarLayout.addView(linearLayoutDaysRow);

        // set month days in calendar view
        for (int j = ONE; j < SEVEN; j++) {
            View calendarRow = layoutInflater.inflate(R.layout.calendar_row, null, false);
            LinearLayout linearLayoutRow = calendarRow.findViewById(R.id.calendar_row);
            for (int i = ONE; i <= SEVEN; i++) {
                View dayItem = layoutInflater.inflate(R.layout.day_layout, null, false);
                final TextView dayTV = dayItem.findViewById(R.id.day_tv);
                dayTV.setBackgroundColor(getColor(context, monthCalender.getResBackgroundColorIdDays()));
                if (d++ < presenter.getIndexOfFirstDay()) {
                    dayTV.setText(EMPTY_STRING);
                } else {
                    if (dayNumber <= presenter.getMonthDaysCount()) {
                        dayTV.setText(String.valueOf(dayNumber++));
                        if (offDays.contains(dayNumber)) {
                            dayTV.setBackgroundColor(getColor(context, monthCalender.getResBackgroundColorIdOffDays()));
                        }
                        dayTV.setTextColor(getColor(context, monthCalender.getResTextColorIdDays()));
                    } else { // next month
                        dayTV.setText(String.valueOf(newMonthDayNumber++));
                        dayTV.setTextColor(getColor(context, android.R.color.darker_gray));
                    }
                    dayTV.setOnClickListener(monthCalender.getOnClickListener());
                }
                linearLayoutRow.addView(dayTV);
            }
            calendarLayout.addView(linearLayoutRow);
        }
    }

    private void setCalendarLabel() {
        calendarLabel.setText(presenter.getCurrentMonthName());
        calendarLabel.setTextColor(getColor(context, monthCalender.getResTextColorIdLabel()));
        calendarLabel.setBackgroundColor(getColor(context, monthCalender.getResBackgroundColorLabel()));
    }

    void setMonthCalender(MonthCalender monthCalender) {
        this.monthCalender = monthCalender;
    }
}
