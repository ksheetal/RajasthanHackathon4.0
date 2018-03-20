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
 * Created by sheetal on 3/19/2018.
 */

public class DietSLideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public DietSLideAdapter(Context context) {
        this.context = context;
    }


    //Arrays
    public int[] slide_image = {
            R.drawable.img1,
            R.drawable.img1,
            R.drawable.img1,
            R.drawable.img1
    };

    public String[] slide_headings = {"Week 1", "Week 2", "Week 3", "Week 4"};

    public String[] slide_desc = {"Breakfast-Breastmilk Or Infant Formula, Morning Tea-Breastmilk Or Infant Formula, Lunch-Breastmilk Or Infant Formula, Afternoon Tea-Breastmilk Or Infant Formula, Dinner-Breastmilk Or Infant Formula",
            "Breakfast-Breastmilk Or Infant Formula, Morning Tea-Breastmilk Or Infant Formula, Lunch-Breastmilk Or Infant Formula, Afternoon Tea-Breastmilk Or Infant Formula, Dinner-Breastmilk Or Infant Formula",
            "Breakfast-Breastmilk Or Infant Formula, Morning Tea-Breastmilk Or Infant Formula, Lunch-Breastmilk Or Infant Formula, Afternoon Tea-Breastmilk Or Infant Formula, Dinner-Breastmilk Or Infant Formula",
            "Breakfast-Breastmilk Or Infant Formula, Morning Tea-Breastmilk Or Infant Formula, Lunch-Breastmilk Or Infant Formula, Afternoon Tea-Breastmilk Or Infant Formula, Dinner-Breastmilk Or Infant Formula"};


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
        View view = layoutInflater.inflate(R.layout.dietslidelayout, container, false);

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
