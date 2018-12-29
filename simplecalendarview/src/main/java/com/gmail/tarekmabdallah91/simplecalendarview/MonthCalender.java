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
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import static com.gmail.tarekmabdallah91.simplecalendarview.MonthCalenderPresenter.ZERO;

public class MonthCalender {

    @SuppressLint("StaticFieldLeak")
    private static volatile MonthCalender monthCalender;
    private final AppCompatActivity activity;
    private View.OnClickListener onClickListener;
    private MonthCalenderFragment monthCalenderFragment;
    private List<Integer> OffDays;
    private int resFrameLayout;
    private int paddingHorizontallyTV = ZERO;
    private int paddingVerticallyTV = ZERO;
    private int resBackgroundColorLabel = android.R.color.black;
    private int resTextColorIdLabel = android.R.color.black;
    private int resTextColorId = android.R.color.darker_gray;
    private int resBackgroundColorIdCurrentDay = android.R.color.black;
    private int resBackgroundColorIdCalendar = android.R.color.white;
    private int resBackgroundColorIdDays = android.R.color.white;
    private int resBackgroundColorIdOffDays = android.R.color.darker_gray;
    private int resTextColorIdDays = android.R.color.black;

    private MonthCalender(AppCompatActivity activity) {
        this.activity = activity;
    }

    public static MonthCalender getInstance(AppCompatActivity activity) {
        if (null == monthCalender) {
            synchronized (MonthCalender.class) {
                monthCalender = new MonthCalender(activity);
            }
        }
        return monthCalender;
    }

    private void setMonthCalenderFragment() {
        this.monthCalenderFragment = MonthCalenderFragment.getInstance();
        this.monthCalenderFragment.setMonthCalender(this);
    }

    public void start() {
        setMonthCalenderFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(resFrameLayout, monthCalenderFragment)
                .commit();
    }

    public void onDestroy() {
        try {
            monthCalender.finalize();
            monthCalender = null;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void notifyDataChanges() {
        setMonthCalenderFragment();
        start();
    }

    int getPaddingHorizontallyTV() {
        return paddingHorizontallyTV;
    }

    public MonthCalender setPaddingHorizontallyTV(int paddingHorizontallyTV) {
        this.paddingHorizontallyTV = paddingHorizontallyTV;
        return this;
    }

    int getPaddingVerticallyTV() {
        return paddingVerticallyTV;
    }

    public MonthCalender setPaddingVerticallyTV(int paddingVerticallyTV) {
        this.paddingVerticallyTV = paddingVerticallyTV;
        return this;
    }

    int getResBackgroundColorLabel() {
        return resBackgroundColorLabel;
    }

    public MonthCalender setResBackgroundColorLabel(int resBackgroundColorLabel) {
        this.resBackgroundColorLabel = resBackgroundColorLabel;
        return this;
    }

    int getResTextColorIdLabel() {
        return resTextColorIdLabel;
    }

    public MonthCalender setResTextColorIdLabel(int resTextColorIdLabel) {
        this.resTextColorIdLabel = resTextColorIdLabel;
        return this;
    }

    int getResTextColorId() {
        return resTextColorId;
    }

    public MonthCalender setResTextColorId(int resTextColorId) {
        this.resTextColorId = resTextColorId;
        return this;
    }

    int getResBackgroundColorIdCurrentDay() {
        return resBackgroundColorIdCurrentDay;
    }

    public MonthCalender setResBackgroundColorIdCurrentDay(int resBackgroundColorIdCurrentDay) {
        this.resBackgroundColorIdCurrentDay = resBackgroundColorIdCurrentDay;
        return this;
    }

    int getResBackgroundColorIdCalendar() {
        return resBackgroundColorIdCalendar;
    }

    public MonthCalender setResBackgroundColorIdCalendar(int resBackgroundColorIdCalendar) {
        this.resBackgroundColorIdCalendar = resBackgroundColorIdCalendar;
        return this;
    }

    int getResBackgroundColorIdDays() {
        return resBackgroundColorIdDays;
    }

    public MonthCalender setResBackgroundColorIdDays(int resBackgroundColorIdDays) {
        this.resBackgroundColorIdDays = resBackgroundColorIdDays;
        return this;
    }

    int getResTextColorIdDays() {
        return resTextColorIdDays;
    }

    public MonthCalender setResTextColorIdDays(int resTextColorIdDays) {
        this.resTextColorIdDays = resTextColorIdDays;
        return this;
    }

    int getResBackgroundColorIdOffDays() {
        return resBackgroundColorIdOffDays;
    }

    public MonthCalender setResBackgroundColorIdOffDays(int resBackgroundColorIdOffDays) {
        this.resBackgroundColorIdOffDays = resBackgroundColorIdOffDays;
        return this;
    }

    List<Integer> getOffDays() {
        return OffDays;
    }

    public MonthCalender setOffDays(List<Integer> offDays) {
        OffDays = offDays;
        return this;
    }

    View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public MonthCalender setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public MonthCalender setResFrameLayout(int resFrameLayout) {
        this.resFrameLayout = resFrameLayout;
        return this;
    }
}
