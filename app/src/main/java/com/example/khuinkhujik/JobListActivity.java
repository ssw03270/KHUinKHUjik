package com.example.khuinkhujik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JobListActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    Button button1; //경영, 사무, 금융, 보험
    Button button2; //연구 및 공학기술
    Button button3; //교육, 법률, 사회복지, 경찰, 소방 및 군인
    Button button4; //보건, 의료
    Button button5; //예술, 디자인, 방송, 스포츠
    Button button6; //미용, 여행, 숙박, 음식, 경비, 돌봄, 청소
    Button button7; //영업, 판매, 운전, 운송
    Button button8; //건설, 채굴
    Button button9; //설치, 정비, 생산
    Button button10; //농림어업직

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        bottomNavigation = findViewById(R.id.bottomNavigationView);

        button1 = (Button)findViewById(R.id.button); //경영, 사무, 금융, 보험
        button2 = (Button)findViewById(R.id.button2); //연구 및 공학기술
        button3 = (Button)findViewById(R.id.button3); //교육, 법률, 사회복지, 경찰, 소방 및 군인
        button4 = (Button)findViewById(R.id.button4); //보건, 의료
        button5 = (Button)findViewById(R.id.button5); //예술, 디자인, 방송, 스포츠
        button6 = (Button)findViewById(R.id.button6); //미용, 여행, 숙박, 음식, 경비, 돌봄, 청소
        button7 = (Button)findViewById(R.id.button7); //영업, 판매, 운전, 운송
        button8 = (Button)findViewById(R.id.button8); //건설, 채굴
        button9 = (Button)findViewById(R.id.button9); //설치, 정비, 생산
        button10 = (Button)findViewById(R.id.button10); //농림어업직

        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        Menu menu = bottomNavigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }
    public void firstButton(View v){
        Intent intent = new Intent(getApplicationContext(),ShowJobActivity.class);
        intent.putExtra("jobNumber",1);
        intent.putExtra("jobCategory",button1.getText());
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobListActivity.this.finish();
    }
    public void secondButton(View v){

        Intent intent = new Intent(getApplicationContext(),ShowJobActivity.class);
        intent.putExtra("jobNumber",2);
        intent.putExtra("jobCategory",button2.getText());
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobListActivity.this.finish();

    }
    public void thirdButton(View v){

        Intent intent = new Intent(getApplicationContext(),ShowJobActivity.class);
        intent.putExtra("jobNumber",3);
        intent.putExtra("jobCategory",button3.getText());
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobListActivity.this.finish();

    }
    public void fourthButton(View v){

        Intent intent = new Intent(getApplicationContext(),ShowJobActivity.class);
        intent.putExtra("jobNumber",4);
        intent.putExtra("jobCategory",button4.getText());
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobListActivity.this.finish();

    }
    public void fifthButton(View v){

        Intent intent = new Intent(getApplicationContext(),ShowJobActivity.class);
        intent.putExtra("jobNumber",5);
        intent.putExtra("jobCategory",button5.getText());
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobListActivity.this.finish();

    }
    public void sixthButton(View v){

        Intent intent = new Intent(getApplicationContext(),ShowJobActivity.class);
        intent.putExtra("jobNumber",6);
        intent.putExtra("jobCategory",button6.getText());
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobListActivity.this.finish();

    }
    public void seventhButton(View v){

        Intent intent = new Intent(getApplicationContext(),ShowJobActivity.class);
        intent.putExtra("jobNumber",7);
        intent.putExtra("jobCategory",button7.getText());
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobListActivity.this.finish();

    }
    public void eighthButton(View v){

        Intent intent = new Intent(getApplicationContext(),ShowJobActivity.class);
        intent.putExtra("jobNumber",8);
        intent.putExtra("jobCategory",button8.getText());
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobListActivity.this.finish();

    }
    public void ninthButton(View v){

        Intent intent = new Intent(getApplicationContext(),ShowJobActivity.class);
        intent.putExtra("jobNumber",9);
        intent.putExtra("jobCategory",button9.getText());
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobListActivity.this.finish();
    }
    public void tenthButton(View v){

        Intent intent = new Intent(getApplicationContext(),ShowJobActivity.class);
        intent.putExtra("jobNumber",10);
        intent.putExtra("jobCategory",button10.getText());
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobListActivity.this.finish();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),SelectorActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        JobListActivity.this.finish();
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
                    JobListActivity.this.finish();
                    return false;
                }
            };
}