package com.example.sheetal.hp;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sheetal on 3/17/2018.
 */

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context) {
        this.context = context;
    }


    //Arrays
    public int[] slide_image = {
            R.drawable.bimg1,
            R.drawable.bimg1,
            R.drawable.bimg1
    };

    public String[] slide_headings = {"LOVE", "CARE", "LEARN"};

    public String[] slide_desc = {"Be the parent today that you want your kids to remember tomorrow.",
            "Having a baby is a life-changer. It gives you a whole other perspective on why you wake up every day",
            "We are here to help you out. Use HappyParenting and be a smart and Happy Parent."};


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ConstraintLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slidelayout, container, false);

        ImageView slide_image_view = (ImageView) view.findViewById(R.id.img);
        TextView slideHeading = (TextView) view.findViewById(R.id.text1);
        TextView slideDesc = (TextView) view.findViewById(R.id.text2);

        slide_image_view.setImageResource(slide_image[position]);
        slideHeading.setText(slide_headings[position]);
        slideDesc.setText(slide_desc[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);

    }

}
