package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class TryInputActivity extends AppCompatActivity {
    String[] inputData = new String[11];     // 0 : 이름, 1 : 생년월일, 2 : 성별, 3 : 거주지, 4 : 연락처,
    // 5 : 이메일, 6 : 최종학력 선택, 7 : 최종학력 학교 이름, 8 : 최종학력 학과, 9 : 입학 날짜, 10 : 졸업 날짜

    String[] question = {"성함이 어떻게 되시나요? \n(ex, 홍길동)", "생년월일 8글자를 입력해주세요 \n(ex, 20201128)",
            "성별이 어떻게 되시나요? \n(ex, 남성)", "거주지를 입력해주세요 \n(ex, ㅇㅇ로 ㅇㅇㅇ번길)",
            "연락처를 입력해주세요 \n(ex, 010-1234-5678)", "이메일을 입력해주세요 \n(ex, ㅇㅇㅇㅇ@ㅇㅇㅇㅇ)",
            "최종학력이 어떻게 되셨나요? \n(ex, 4년제 대학교)", "학과가 어떻게 되셨나요? \n(ex, ㅇㅇㅇ학과, 없으신 경우 x)",
            "입학 날짜를 입력해주세요 \n(ex, ㅇㅇㅇㅇ년 ㅇㅇ월)", "졸업 날짜를 입력해주세요 \n(ex, ㅇㅇㅇㅇ년 ㅇㅇ월"};

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
                tts.speak(textQuestion.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        }, 1000);
    }

    public void tryButton(View v){
        if(touchCnt >= question.length - 1){
            Log.d("input", "end");

        }else{
            inputData[touchCnt] = String.valueOf(editAnswer.getText());
            Log.d("input", inputData[touchCnt]);
            touchCnt += 1;
            editAnswer.setText("");
            textQuestion.setText(question[touchCnt]);
            tts.speak(textQuestion.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
        }


    }
}