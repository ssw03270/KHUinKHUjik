package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class ReadyActivity extends AppCompatActivity {

    private TextToSpeech tts;
    private TextView introText;
    private int touchCnt = 0;
    String tryType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);

        Intent intent = getIntent();
        tryType = intent.getExtras().getString("tryType");

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
        if(tryType.equals("video")){
            introText.setText("영상 촬영 방식을 선택하셨습니다");
        }else if(tryType.equals("document")){
            introText.setText("서류 작성 방식을 선택하셨습니다");
        }

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
                introText.setText("이후 안내되는 사항을 숙지하시고 \n그에 맞게 준비하시면 되겠습니다");
                tts.speak(introText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }else{
                Intent intent = new Intent(getApplication(), SelectorActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                ReadyActivity.this.finish();
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