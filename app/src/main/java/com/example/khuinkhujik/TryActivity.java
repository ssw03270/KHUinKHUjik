package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class TryActivity extends AppCompatActivity {

    private TextToSpeech tts;
    private TextView introText;
    private int touchCnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        introText = findViewById(R.id.textView);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tts.speak(introText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        }, 1000);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN || event.getAction()==MotionEvent.ACTION_MOVE){
            touchCnt += 1;
            if(touchCnt == 1){
                introText.setText("지원서 작성 방식으로는 \n 총 두가지가 존재합니다");
                tts.speak(introText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }else if(touchCnt == 2){
                introText.setText("영상과 서류 중에서 \n 어떤 방식으로 지원하시겠습니까?");
                tts.speak(introText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }else{
                Intent intent = new Intent(getApplication(), TrySelectorActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                TryActivity.this.finish();
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TTS 객체가 남아있다면 실행을 중지하고 메모리에서 제거한다.
        if(tts != null){
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }
}