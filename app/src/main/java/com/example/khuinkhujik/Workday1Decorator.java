package com.example.khuinkhujik;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;

class Workday1Decorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();

    public Workday1Decorator(){
    }

    public boolean shouldDecorate(CalendarDay day){
        day.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return (weekDay==Calendar.MONDAY||weekDay==Calendar.WEDNESDAY||weekDay==Calendar.FRIDAY);
    }

    public void decorate(DayViewFacade view){
        view.addSpan(new ForegroundColorSpan(Color.BLUE));
        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new RelativeSizeSpan(1.4f));
        view.addSpan(new DotSpan(5,Color.RED));
    }
}
