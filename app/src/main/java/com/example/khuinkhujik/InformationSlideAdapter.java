package com.example.khuinkhujik;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class InformationSlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public InformationSlideAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images={
            R.drawable.infor1,
            R.drawable.infor2,
            R.drawable.infor3,
            R.drawable.infor4,
            R.drawable.infor5,
            R.drawable.infor6,
            R.drawable.infor7,
    };

    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_information_slide, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);

        slideImageView.setImageResource(slide_images[position]);

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
