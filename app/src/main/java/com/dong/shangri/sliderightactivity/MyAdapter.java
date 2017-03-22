package com.dong.shangri.sliderightactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.BaseAdapter;

/**
 * Created by shangri on 17-3-20.
 */

public class MyAdapter extends FragmentPagerAdapter {
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new PageFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
