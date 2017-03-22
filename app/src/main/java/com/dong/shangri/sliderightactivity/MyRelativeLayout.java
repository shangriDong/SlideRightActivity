package com.dong.shangri.sliderightactivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import java.util.logging.Logger;

/**
 * Created by shangri on 17-3-20.
 */

public class MyRelativeLayout extends RelativeLayout {
    private Logger log = Logger.getLogger(MyRelativeLayout.class.getName());
    private Scroller mScroller;
    private ViewGroup mParentView;
    private int downX;
    private int downY;
    private int tempX;
    private int viewWidth;
    private boolean isSilding;
    private int mTouchSlop;
    private boolean isFinish;

    private OnListener onListener;

    public MyRelativeLayout(Context context) {
        super(context);
        init();
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            mParentView = (ViewGroup) this.getParent();
            viewWidth = this.getWidth();
        }
    }

    /**
     * 滚动出界面
     */
    private void scrollRight() {
        final int delta = (viewWidth + mParentView.getScrollX());
        // 调用startScroll方法来设置一些滚动的参数，我们在computeScroll()方法中调用scrollTo来滚动item
        mScroller.startScroll(mParentView.getScrollX(), 0, -delta + 1, 0,
                Math.abs(delta));
        postInvalidate();
    }

    /**
     * 滚动到起始位置
     */
    private void scrollOrigin() {
        int delta = mParentView.getScrollX();
        mScroller.startScroll(mParentView.getScrollX(), 0, -delta, 0,
                Math.abs(delta));
        postInvalidate();
    }

    private void init() {
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mScroller = new Scroller(getContext());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (ev.getX() < 40) {
                    log.info("return true;");
                    return true;
                } else {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        // 调用startScroll的时候scroller.computeScrollOffset()返回true，
        if (mScroller.computeScrollOffset()) {
            mParentView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < 40) {
                    downX = tempX = (int) event.getRawX();
                    downY = (int) event.getRawY();
                    log.info("ev getX: " + event.getX());
                    return true;
                } else {
                    return false;
                }
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getRawX();
                int deltaX = tempX - moveX;
                tempX = moveX;
                if (Math.abs(moveX - downX) > mTouchSlop &&
                        Math.abs((int) event.getRawY() - downY) < mTouchSlop) {
                    isSilding = true;
                }

                if (moveX - downX >= 0 && isSilding) {
                    mParentView.scrollBy(deltaX, 0);
                }
                log.info("moveX: " + moveX + " deltaX: " + deltaX);
                break;
            case MotionEvent.ACTION_UP:
                isSilding = false;
                if (event.getRawX() <= viewWidth / 2) {
                    isFinish = true;
                    scrollRight();

                    if (onListener != null) {
                        onListener.OnFinish();
                    }
                } else {
                    scrollOrigin();
                    isFinish = false;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    public void setOnListener(OnListener onListener) {
        this.onListener = onListener;
    }

    public interface OnListener {
        void OnFinish();
    }
}
