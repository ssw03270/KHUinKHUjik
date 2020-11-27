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

public class Try2Activity extends AppCompatActivity {
    private TextToSpeech tts;
    private TextView introText;
    private int touchCnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try2);

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
                introText.setText("이제 영상 녹화를 진행하도록 하겠습니다");
                tts.speak(introText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }else if(touchCnt == 2){
                introText.setText("준비가 되셨다면 화면을 클릭하시고 \n 준비하신 발표 진행하시면 됩니다");
                tts.speak(introText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }else if(touchCnt == 3){
                introText.setText("좋은 결과 있으시길 바랍니다");
                tts.speak(introText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }else{
                Intent intent = new Intent(getApplication(), TryRecordActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                Try2Activity.this.finish();
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