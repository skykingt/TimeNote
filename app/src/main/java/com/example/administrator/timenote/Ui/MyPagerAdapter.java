package com.example.administrator.timenote.Ui;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyPagerAdapter extends PagerAdapter  {
    private ArrayList<View> viewLists;

    public MyPagerAdapter() {
    }

    public MyPagerAdapter(ArrayList<View> viewLists) {
        super();
        this.viewLists = viewLists;
    }

    public int getCount() {
        return viewLists.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }
}