package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.TypefaceCompatApi26Impl;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ShowJobActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_job);

        readCSVFIle();
    }

    private void readCSVFIle() {
        Intent intent = getIntent();
        int whatCSVFile = intent.getExtras().getInt("jobNumber");
        int[] csvFile =  {R.raw.csv1, R.raw.csv2, R.raw.csv3, R.raw.csv4, R.raw.csv5, R.raw.csv6, R.raw.csv7, R.raw.csv8, R.raw.csv9, R.raw.csv10};

        TextView textCategory = (TextView)findViewById(R.id.textCategory);
        textCategory.setText(intent.getExtras().getString("jobCategory"));

        InputStream is = getResources().openRawResource(csvFile[whatCSVFile - 1]);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line="";
        try{
            while((line=reader.readLine())!=null){
                String[] tokens = line.split(",");
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

                LinearLayout.LayoutParams layoutParams_linear = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams_linear.setMargins(0, 50, 0, 0);

                LinearLayout linearLayout1 = new LinearLayout(this);
                linearLayout1.setBackground(ContextCompat.getDrawable(this, R.drawable.box_type2));
                linearLayout1.setLayoutParams(layoutParams_linear);
                linearLayout1.setOrientation(LinearLayout.VERTICAL);

                ViewGroup.LayoutParams layoutParams_textview = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView textView1 = new TextView(this);
                textView1.setText(tokens[0]);
                textView1.setTextSize(30);
                textView1.setPadding(50, 50, 50, 0);
                textView1.setLayoutParams(layoutParams_textview);
                textView1.setTypeface(getResources().getFont(R.font.notosans));
                textView1.setTextColor(Color.parseColor("#000000"));
                linearLayout1.addView(textView1);

                LinearLayout linearLayout2 = new LinearLayout(this);
                linearLayout2.setOrientation(LinearLayout.HORIZONTAL);

                TextView textView2 = new TextView(this);
                textView2.setText(tokens[1]);
                textView2.setTextSize(15);
                textView2.setPadding(50, 25, 0, 50);
                textView2.setLayoutParams(layoutParams_textview);
                textView2.setTypeface(getResources().getFont(R.font.notosans));
                textView2.setTextColor(Color.parseColor("#000000"));
                linearLayout2.addView(textView2);

                TextView textView3 = new TextView(this);
                textView3.setText(tokens[2]);
                textView3.setTextSize(15);
                textView3.setPadding(50, 25, 0, 50);
                textView3.setLayoutParams(layoutParams_textview);
                textView3.setTypeface(getResources().getFont(R.font.notosans));
                textView3.setTextColor(Color.parseColor("#000000"));
                linearLayout2.addView(textView3);

                TextView textView4 = new TextView(this);
                textView4.setText(tokens[3]);
                textView4.setTextSize(15);
                textView4.setPadding(50, 25, 0, 50);
                textView4.setLayoutParams(layoutParams_textview);
                textView4.setTypeface(getResources().getFont(R.font.notosans));
                textView4.setTextColor(Color.parseColor("#000000"));
                linearLayout2.addView(textView4);

                linearLayout1.addView(linearLayout2);

                View.OnClickListener clickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("log01",tokens[0]);

                        Intent intent = new Intent(getApplicationContext(),JobActivity.class);

                        intent.putExtra("jobTitle", tokens[0]);
                        intent.putExtra("jobPay", tokens[1]);
                        intent.putExtra("jobDay", tokens[2]);
                        intent.putExtra("jobTime", tokens[3]);
                        intent.putExtra("jobWork", tokens[4]);
                        intent.putExtra("jobCondition", tokens[5]);
                        intent.putExtra("jobURL", tokens[6]);

                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        ShowJobActivity.this.finish();

                    }
                };
                linearLayout1.setOnClickListener(clickListener);

                linearLayout.addView(linearLayout1);

            }

        }catch (IOException e){
            Log.d("ShowJobActivity", "error");
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){

            Intent intent = new Intent(getApplication(), JobActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            ShowJobActivity.this.finish();
        }
        return true;
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),JobListActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        ShowJobActivity.this.finish();
    }
}