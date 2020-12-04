package com.example.khuinkhujik;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TryRecordActivity extends AppCompatActivity {

    Button buttonRecord;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_record);

        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        buttonRecord  = (Button)findViewById(R.id.buttonRecord);

        buttonRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            VideoView videoView = new VideoView(this);
            videoView.setVideoURI(data.getData());
            videoView.start();
            builder.setView(videoView);

            Intent intent = new Intent(getApplication(), ResultActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            TryRecordActivity.this.finish();
        }
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),JobListActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        TryRecordActivity.this.finish();
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
                    TryRecordActivity.this.finish();
                    return false;
                }
            };
}