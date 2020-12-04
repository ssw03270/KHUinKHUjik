package com.example.khuinkhujik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class JobActivity extends AppCompatActivity {

    TextView textTitle, textPay, textDay, textTime, textWork, textCondition;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        Intent intent = getIntent();
        String jobTitle = intent.getExtras().getString("jobTitle");
        String jobPay = intent.getExtras().getString("jobPay");
        String jobDay = intent.getExtras().getString("jobDay");
        String jobTime = intent.getExtras().getString("jobTime");
        String jobWork = intent.getExtras().getString("jobWork");
        String jobCondition = intent.getExtras().getString("jobCondition");

        textTitle = findViewById(R.id.textTitle);
        textPay = findViewById(R.id.textPay);
        textDay = findViewById(R.id.textDay);
        textTime = findViewById(R.id.textTime);
        textWork = findViewById(R.id.textWork);
        textCondition = findViewById(R.id.textCondition);

        textTitle.setText(jobTitle);
        textPay.setText("임금 : " + jobPay);
        textDay.setText("근무형태 : " + jobDay);
        textTime.setText("근무시간 : " + jobTime);
        textWork.setText("직무내용 : " + jobWork);
        textCondition.setText("경력조건 : " + jobCondition);

        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    public void infoButton(View v){
        Intent intentUrl = getIntent();
        String jobURL = intentUrl.getExtras().getString("jobURL");

        Log.d("log01", jobURL);
        Uri uri = Uri.parse(jobURL); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void tryButton(View v){
        SharedPreferences sp = getSharedPreferences("inputData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        Intent intentFile = getIntent();
        editor.putString("jobTitle", intentFile.getExtras().getString("jobTitle"));
        editor.putString("jobPay", intentFile.getExtras().getString("jobPay"));
        editor.putString("jobDay", intentFile.getExtras().getString("jobDay"));
        editor.putString("jobTime", intentFile.getExtras().getString("jobTime"));
        editor.putString("jobWork", intentFile.getExtras().getString("jobWork"));
        editor.putString("jobCondition", intentFile.getExtras().getString("jobCondition"));
        editor.putString("jobURL", intentFile.getExtras().getString("jobURL"));
        editor.apply();

        Intent intent = new Intent(getApplication(), TryActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobActivity.this.finish();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),JobListActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobActivity.this.finish();
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
                    JobActivity.this.finish();
                    return false;
                }
            };
}