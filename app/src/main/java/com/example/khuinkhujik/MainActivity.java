package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView introText;
    private int touchCnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        introText = findViewById(R.id.textView);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN || event.getAction()==MotionEvent.ACTION_MOVE){
            touchCnt += 1;
            if(touchCnt == 1){
                introText.setText("저는 여러분이 '쿠인쿠직'을 \n잘 사용할 수 있도록 \n도와드릴거에요.");
            }else if(touchCnt == 2){
                introText.setText("그럼 같이 좋은 일자리를 \n 찾으러 가볼까요?");
            }else{
                Intent intent = new Intent(getApplication(), SelectorActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                MainActivity.this.finish();
            }
        }
        return true;
    }

}