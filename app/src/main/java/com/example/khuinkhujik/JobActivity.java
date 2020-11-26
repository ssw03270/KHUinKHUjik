package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class JobActivity extends AppCompatActivity {

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
        String jobURL = intent.getExtras().getString("jobURL");


        Log.d("log01", jobTitle + jobPay + jobDay + jobTime + jobWork + jobCondition + jobURL);


    }

}