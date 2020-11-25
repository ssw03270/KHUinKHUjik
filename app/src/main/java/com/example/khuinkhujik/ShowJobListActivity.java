package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class ShowJobListActivity extends AppCompatActivity {

    private TextView introText;
    private int touchCnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_job_list);

        introText = findViewById(R.id.textView);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN || event.getAction()==MotionEvent.ACTION_MOVE){
            touchCnt += 1;
            if(touchCnt == 1){
                introText.setText("총 10개의 직종을 \n보여드리겠습니다");
            }else if(touchCnt == 2){
                introText.setText("원하시는 직종을 골라주세요");
            }else{
                Intent intent = new Intent(getApplication(), JobListActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                ShowJobListActivity.this.finish();
            }
        }
        return true;
    }
}