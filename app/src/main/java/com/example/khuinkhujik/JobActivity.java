package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class JobActivity extends AppCompatActivity {

    TextView textTitle, textPay, textDay, textTime, textWork, textCondition;
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
        Log.d("log01", "aaaa");
    }
}