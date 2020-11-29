package com.example.khuinkhujik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {

    Button findJobBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        MaterialCalendarView materialCalendarView = findViewById(R.id.materialCalendarView);
        materialCalendarView.addDecorators(
                new Workday1Decorator()
        );
        materialCalendarView.setSelectedDate(CalendarDay.today());

        TextView todayTextView = (TextView)findViewById(R.id.todayTextView);
        TextView workdayTextView = (TextView)findViewById(R.id.workDayTextView);

        Calendar cal = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
        String date = format.format(Calendar.getInstance().getTime());

        todayTextView.setText(date);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        if(weekDay==Calendar.MONDAY ||weekDay==Calendar.WEDNESDAY||weekDay==Calendar.FRIDAY){
            workdayTextView.setText("오늘은 근무일 입니다.");
        }else{
            workdayTextView.setText("오늘은 근무일이 아닙니다.");
        }
    }





}