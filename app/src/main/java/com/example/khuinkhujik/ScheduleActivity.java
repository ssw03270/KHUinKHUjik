package com.example.khuinkhujik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {

    static CalendarDay selectedDay = null;
    static boolean Selected;
    String DATE;

    Button findJobBtn;
    BottomNavigationView bottomNavigation;

    int month_day[]={ 31,28,31,30,31,30,31,31,30,31,30,31 };
    void IsLeapYear(int year){ //윤년확인
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
            month_day[1] = 29;
    }

    // 날짜이름
    int getDayName(int year, int month, int day) //요일 구하는 함수
    {
        int total_day = 0;

        // 년
        IsLeapYear(year);

        // 달
        for(int i=1; i<month; i++)
        {
            total_day += month_day[i-1];
        }
        // 일
        total_day += day-1;

        // 2016.1.1 = 금
        int answer_day = (3 + total_day) % 7;
        if(answer_day==1 || answer_day==3||answer_day==5){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        Menu menu = bottomNavigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        MaterialCalendarView materialCalendarView = findViewById(R.id.materialCalendarView);
        materialCalendarView.addDecorators(
                new Workday1Decorator()
        );
        materialCalendarView.setSelectedDate(CalendarDay.today());

        TextView todayTextView = (TextView)findViewById(R.id.todayTextView);
        TextView workdayTextView = (TextView)findViewById(R.id.workDayTextView);
        TextView selectedTextView = (TextView)findViewById(R.id.selectDayTextView);

        Calendar cal = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
        String date = format.format(Calendar.getInstance().getTime());

        todayTextView.setText(date);
        selectedTextView.setText(date);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        if(weekDay==Calendar.MONDAY ||weekDay==Calendar.WEDNESDAY||weekDay==Calendar.FRIDAY){
            workdayTextView.setText(readCSVFIle());
        }else{
            workdayTextView.setText("은 근무일이 아닙니다.");
        }

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                    DATE = date.toString();
                    String ary[] = DATE.split("\\{",2);
                    ary[1] = ary[1].replaceAll("\\}","");

                    String yyyymmdd[] = ary[1].split("-",3); // date.toString() 하면 일월해 중 월이 1 적게 나와 더해주기
                    int tempMonth = Integer.parseInt(yyyymmdd[1]);
                    tempMonth++;
                    yyyymmdd[1] = Integer.toString(tempMonth);
                    if(getDayName(Integer.parseInt(yyyymmdd[0]),Integer.parseInt(yyyymmdd[1]),Integer.parseInt(yyyymmdd[2]))==1){
                        workdayTextView.setText(readCSVFIle());
                    }else{
                        workdayTextView.setText(("은 근무일이 아닙니다."));
                    }

                    selectedTextView.setText(yyyymmdd[0]+"년 "+yyyymmdd[1]+"월 "+yyyymmdd[2]+"일");
            }
        });


    }

    private String readCSVFIle(){
        Intent intent = getIntent();
        int[] csvFile =  {R.raw.csv1, R.raw.csv2, R.raw.csv3, R.raw.csv4, R.raw.csv5, R.raw.csv6, R.raw.csv7, R.raw.csv8, R.raw.csv9, R.raw.csv10};

        InputStream is = getResources().openRawResource(csvFile[0]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line="";
        try{
            line=reader.readLine();
            String[] tokens = line.split(",");
            return (tokens[3]+"\n금강농자재철강 직원");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),SelectorActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        ScheduleActivity.this.finish();
    }
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.person:
                            intent = new Intent(getApplicationContext(),JobListActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.home:
                            intent = new Intent(getApplicationContext(),SelectorActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.calander:
                            intent = new Intent(getApplicationContext(),ScheduleActivity.class);
                            startActivity(intent);
                            break;
                    }
                    overridePendingTransition(0, 0);
                    ScheduleActivity.this.finish();
                    return false;
                }
            };
}