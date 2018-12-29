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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

final class MonthCalenderPresenter {

    static final int ONE = 1;
    static final int ZERO = 0;
    static final int SEVEN = 7;
    private static final int INVALID = -1;
    private static MonthCalenderPresenter presenter;
    private final int indexOfFirstDay;
    private final String WEEK_DAYS = "MON,TUE,WED,THU,FRI,SAT,SUN";
    private final ArrayList<String> WEEK_DAYS_LIST = new ArrayList<>(Arrays.asList(WEEK_DAYS.split(",")));
    private final Calendar calendar;
    private final int YEAR;
    private int monthDaysCount;
    private String firstDayInMonth;

    private MonthCalenderPresenter() {
        calendar = Calendar.getInstance();
        int MONTH = calendar.get(Calendar.MONTH) + ONE;
        YEAR = calendar.get(Calendar.YEAR);
        setMonthDaysCount(MONTH, YEAR);
        int CURRENT_DAY = calendar.get(Calendar.DAY_OF_MONTH);
        setFirstDayInMonth(CURRENT_DAY, MONTH, YEAR);
        indexOfFirstDay = setIndexOfFirstDay(firstDayInMonth, WEEK_DAYS_LIST);
    }

    static MonthCalenderPresenter getInstance() {
        if (null == presenter) presenter = new MonthCalenderPresenter();
        return presenter;
    }

    private void setFirstDayInMonth(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.MONTH, month - ONE);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_MONTH, ONE);
        Date firstDayOfMonth = cal.getTime();
        final DateFormat sdf = new SimpleDateFormat("EEEEEEEE", Locale.US);
        this.firstDayInMonth = sdf.format(firstDayOfMonth);
    }

    private int setIndexOfFirstDay(String dayName, List<String> days) {
        for (int i = ZERO; i < days.size(); i++)
            if (dayName.toLowerCase().equals(days.get(i).toLowerCase()))
                return i;
        return INVALID;
    }

    private void setMonthDaysCount(int month, int year) {
        // Create a calendar object and set year and month
        Calendar gregorianCal = new GregorianCalendar(year, month, ONE);
        // Get the number of days in that month
        this.monthDaysCount = gregorianCal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    int getMonthDaysCount() {
        return monthDaysCount;
    }

    ArrayList<String> getWEEK_DAYS_LIST() {
        return WEEK_DAYS_LIST;
    }

    int getIndexOfFirstDay() {
        return indexOfFirstDay;
    }

    String getCurrentMonthName() {
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + YEAR;
    }
}
