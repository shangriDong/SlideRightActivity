package com.dong.shangri.sliderightactivity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.logging.Logger;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private Logger log = Logger.getLogger(MainActivity.class.getName());
    private View root;
    /*private GestureDetector mGestureDetector;*/
    private MyViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = getContentView(this);
        viewPager = (MyViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        /*mGestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
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

        mGestureDetector.setIsLongpressEnabled(false);
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGestureDetector.onTouchEvent(event);
            }
        });*/

        /*root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log.info("asdfasdfasdf");
            }
        });*/

    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static View getContentView(Activity ac) {
        ViewGroup view = (ViewGroup) ac.getWindow().getDecorView();
        FrameLayout content = (FrameLayout) view.findViewById(android.R.id.content);
        return content.getChildAt(0);
    }
}
