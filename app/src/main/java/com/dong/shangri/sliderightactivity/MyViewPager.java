package com.dong.shangri.sliderightactivity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.logging.Logger;

/**
 * Created by shangri on 17-3-20.
 */

public class MyViewPager extends ViewPager {
    private Logger log = Logger.getLogger(MyViewPager.class.getName());
    private GestureDetector mGestureDetector;
    public MyViewPager(Context context) {
        super(context);
        //init();
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //init();
    }

    private void init() {
        mGestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                log.info("e1, x: " + e1.getX() + " y: " + e1.getY());
                log.info("e2, x: " + e2.getX() + " y: " + e2.getY());
                if (e1.getX() <20) {
                    return false;
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

    }

    /*@Override
    public boolean onTouchEvent(MotionEvent ev) {
        *//*if (!mGestureDetector.onTouchEvent(ev)) {
            return false;
        } else {*//*
        if (mGestureDetector.onTouchEvent(ev)) {
            log.info("true");
        } else {
            log.info("false");
        }
        return super.onTouchEvent(ev);

        //}
    }*/

    private OnViewPagerTouchEvent listener;

    public void setOnViewPagerTouchEventListener(OnViewPagerTouchEvent l){
        listener = l;
    }

    public interface OnViewPagerTouchEvent{
        void onTouchDown();
        void onTouchUp();
    }
}
