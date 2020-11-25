package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectorActivity extends AppCompatActivity {

    Button findJobBtn;
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

}