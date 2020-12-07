package com.example.khuinkhujik;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class FunctionInformationActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private ImageView prevImageView;
    private ImageView nextImageView;
    private TextView textView;
    private InformationSlideAdapter informationSlideAdapter;

    boolean canNextPage = false;

    int mCurrentPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_information);

        textView = (TextView)findViewById(R.id.nextPageTextView);

        mSlideViewPager = (ViewPager)findViewById(R.id.slideViewPager);

        prevImageView = (ImageView)findViewById(R.id.prevImageView);
        nextImageView = (ImageView)findViewById(R.id.nextImageView);

        informationSlideAdapter = new InformationSlideAdapter(this);

        mSlideViewPager.setAdapter(informationSlideAdapter);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        prevImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(mCurrentPage > 0) mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
        nextImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(mCurrentPage < 7) mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                if(mCurrentPage == 7){
                    Intent intent = new Intent(getApplicationContext(),SelectorActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    FunctionInformationActivity.this.finish();
                }
            default:
                break;
        }
        return true;
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            mCurrentPage = position;

            if(position == 7){
                nextImageView.setVisibility(View.INVISIBLE);
                prevImageView.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(),SelectorActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                FunctionInformationActivity.this.finish();
            }else if(position == 0){
                prevImageView.setVisibility(View.INVISIBLE);
                nextImageView.setVisibility(View.VISIBLE);
                textView.setText("");
                canNextPage = false;
            }else if(position==6){
                nextImageView.setVisibility(View.VISIBLE);
                prevImageView.setVisibility(View.VISIBLE);
                textView.setText("다음 페이지로 넘기면 앱이 실행됩니다.");
            }else{
                nextImageView.setVisibility(View.VISIBLE);
                prevImageView.setVisibility(View.VISIBLE);
                textView.setText("");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
