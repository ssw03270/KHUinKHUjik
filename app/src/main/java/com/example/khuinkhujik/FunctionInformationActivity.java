package com.example.khuinkhujik;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class FunctionInformationActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private TextView textView;
    private InformationSlideAdapter informationSlideAdapter;

    boolean canNextPage = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_information);

        mSlideViewPager = (ViewPager)findViewById(R.id.slideViewPager);
        textView = (TextView)findViewById(R.id.scrollInfoTextView);

        informationSlideAdapter = new InformationSlideAdapter(this);

        mSlideViewPager.setAdapter(informationSlideAdapter);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(canNextPage){
                    Intent intent = new Intent(getApplicationContext(),SelectorActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    FunctionInformationActivity.this.finish();
                }else{}
            }
        });
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(position==6){
                textView.setText("여기를 터치하시면\n앱을 시작할 수 있습니다");
                canNextPage=true;
            }else{
                textView.setText("다음으로 넘어가려면\n오른쪽으로 넘기세요"); //
                canNextPage=false;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
