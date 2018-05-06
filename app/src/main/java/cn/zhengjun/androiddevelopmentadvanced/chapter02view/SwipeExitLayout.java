package cn.zhengjun.androiddevelopmentadvanced.chapter02view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2018/5/4
 * Summary : 在这里描述Class的主要功能
 */

public class SwipeExitLayout extends LinearLayout {
    private int startX;
    private int startY;
    private Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public SwipeExitLayout(Context context) {
        super(context);
    }

    public SwipeExitLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("SwipeExitLayout.dispatchTouchEvent  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("SwipeExitLayout.dispatchTouchEvent  ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("SwipeExitLayout.dispatchTouchEvent  ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("SwipeExitLayout.dispatchTouchEvent  ACTION_CANCEL");
                break;
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(ev);
        System.out.println("SwipeExitLayout.dispatchTouchEvent 返回 " + dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int evX = (int) ev.getX();
        int evY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("SwipeExitLayout.onInterceptTouchEvent  ACTION_DOWN");
                startX = evX;
                startY = evY;
                return false;
//                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("SwipeExitLayout.onInterceptTouchEvent  ACTION_MOVE");
                int deltaX = evX - startX;
                int deltaY = evY - startY;
                intercepted = deltaX >= 100 && (deltaY == 0 || Math.abs(deltaX / deltaY) >= 3);
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("SwipeExitLayout.onInterceptTouchEvent  ACTION_UP");
                break;
        }
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int evX = (int) event.getX();
        int evY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("SwipeExitLayout.onTouchEvent  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("SwipeExitLayout.onTouchEvent  ACTION_MOVE");
                int deltaX = evX - startX;
                int deltaY = evY - startY;
                if (deltaX >= 100 && (deltaY == 0 || Math.abs(deltaX / deltaY) >= 3)) {
                    activity.finish();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("SwipeExitLayout.onTouchEvent  ACTION_UP");
                break;
        }
        return false;
    }

    private static final String TAG = "SwipeExitLayout";
}
