package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectorActivity extends AppCompatActivity {

    Button findJobBtn;

    String tell="tel:01072737748";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        findJobBtn = (Button)findViewById(R.id.button);

    }

    public void findJob(View v){
        Intent intent = new Intent(getApplicationContext(),ShowJobListActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        SelectorActivity.this.finish();
    }
    public void writeWorkSchedule(View v){
        Intent intent = new Intent(getApplicationContext(),ScheduleActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        SelectorActivity.this.finish();
    }
    public void inquire(View  v){
        startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tell)));
    }
    public void checkTry(View v){
        Intent intent = new Intent(getApplicationContext(),CheckTryActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        SelectorActivity.this.finish();
    }
}