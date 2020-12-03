package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class TryInputActivity extends AppCompatActivity {
    String[] inputData = new String[4];     // 0 : 이름, 1 : 생년월일, 2 : 성별, 3 : 연락처

    String[] question = {"성함이 어떻게 되시나요? \n(ex, 홍길동)", "생년월일 8글자를 입력해주세요 \n(ex, 20201128)",
            "성별이 어떻게 되시나요? \n(ex, 남성)", "연락처를 입력해주세요 \n(ex, 010-1234-5678)",};

    TextView textQuestion;
    EditText editAnswer;
    Button buttonTry;

    private TextToSpeech tts;
    private int touchCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_input);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        textQuestion = (TextView)findViewById(R.id.textQuestion);
        editAnswer = (EditText) findViewById(R.id.editAnswer);
        buttonTry = (Button) findViewById(R.id.buttonTry);

        textQuestion.setText(question[0]);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("inputData", MODE_PRIVATE);
                if(sp.getBoolean("micOn", true)) {
                    tts.speak(textQuestion.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        }, 1000);
    }

    public void tryButton(View v){
        if(touchCnt >= question.length - 1){
            Intent intent = new Intent(getApplication(), Try2Activity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            TryInputActivity.this.finish();
        }else{
            inputData[touchCnt] = String.valueOf(editAnswer.getText());
            Log.d("input", inputData[touchCnt]);
            touchCnt += 1;
            editAnswer.setText("");
            textQuestion.setText(question[touchCnt]);
            SharedPreferences sp = getSharedPreferences("inputData", MODE_PRIVATE);
            if(sp.getBoolean("micOn", true)) {
                tts.speak(textQuestion.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
            LinearLayout percentageLeft = (LinearLayout)findViewById(R.id.percentageLeft), percentageRight = (LinearLayout)findViewById(R.id.percentageRight);
            LinearLayout.LayoutParams lpLeft = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, percentageLeft.getHeight());
            LinearLayout.LayoutParams lpRight = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, percentageRight.getHeight());
            switch(touchCnt){
                case 1:
                    lpLeft.weight = 0.375f;
                    lpRight.weight = 0.625f;
                    break;
                case 2:
                    lpLeft.weight = 0.25f;
                    lpRight.weight = 0.75f;
                    break;
                case 3:
                    lpLeft.weight = 0.125f;
                    lpRight.weight = 0.875f;
                    break;
            }
            percentageLeft.setLayoutParams(lpLeft);
            percentageRight.setLayoutParams(lpRight);
        }
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),JobListActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        TryInputActivity.this.finish();
    }
}