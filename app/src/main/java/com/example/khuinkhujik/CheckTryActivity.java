package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CheckTryActivity extends AppCompatActivity {

    TextView textTitle, textPay, textDay, textTime, textWork, textCondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_try);

        SharedPreferences sp = getSharedPreferences("inputData", MODE_PRIVATE);
        String jobTitle = sp.getString("jobTitle", "");
        String jobPay = sp.getString("jobPay", "");
        String jobDay = sp.getString("jobDay", "");
        String jobTime = sp.getString("jobTime", "");
        String jobWork = sp.getString("jobWork", "");
        String jobCondition = sp.getString("jobCondition", "");


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
        SharedPreferences sp = getSharedPreferences("inputData", MODE_PRIVATE);
        String jobURL = sp.getString("jobURL", "");

        Log.d("log01", jobURL);
        Uri uri = Uri.parse(jobURL); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}