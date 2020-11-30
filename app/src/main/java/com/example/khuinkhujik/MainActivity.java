package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.*;
import android.widget.TextView;

import static android.speech.tts.TextToSpeech.ERROR;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech tts;
    private TextView introText;
    private int touchCnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            touchCnt += 1;
            if(touchCnt == 1){
                introText.setText("저는 여러분이 '쿠인쿠직'을 \n잘 사용할 수 있도록 \n도와드릴거에요.");
                tts.speak(introText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }else if(touchCnt == 2){
                introText.setText("그럼 같이 좋은 일자리를 \n 찾으러 가볼까요?");
                tts.speak(introText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }else{
                Intent intent = new Intent(getApplication(), SelectorActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                MainActivity.this.finish();
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