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
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("SwipeExitLayout.onInterceptTouchEvent  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("SwipeExitLayout.onInterceptTouchEvent  ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("SwipeExitLayout.onInterceptTouchEvent  ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("SwipeExitLayout.onInterceptTouchEvent  ACTION_CANCEL");
                break;
        }
//        boolean intercepted = false;
//        int evX = (int) ev.getX();
//        int evY = (int) ev.getY();
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                System.out.println("SwipeExitLayout.onInterceptTouchEvent  MotionEvent.ACTION_DOWN");
//                startX = evX;
//                startY = evY;
//                return false;
////                break;
//            case MotionEvent.ACTION_MOVE:
//                System.out.println("SwipeExitLayout.onInterceptTouchEvent  MotionEvent.ACTION_MOVE");
//                int deltaX = evX - startX;
//                System.out.println("deltaX = " + deltaX);
//                int abs = Math.abs(deltaX / (evY - startY));
//                System.out.println("abs = " + abs);
//                if (abs > 3 && deltaX > 100) {
//                    System.out.println("Bingo!");
//                    intercepted = true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                System.out.println("SwipeExitLayout.onInterceptTouchEvent  MotionEvent.ACTION_UP");
//                break;
//        }
//        return intercepted;
        System.out.println("super.onInterceptTouchEvent(ev) = " + super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("SwipeExitLayout.onTouchEvent  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("SwipeExitLayout.onTouchEvent  ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("SwipeExitLayout.onTouchEvent  ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("SwipeExitLayout.onTouchEvent  ACTION_CANCEL");
                break;
        }
//        int evX = (int) event.getX();
//        int evY = (int) event.getY();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                System.out.println("SwipeExitLayout.onTouchEvent  MotionEvent.ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                System.out.println("SwipeExitLayout.onTouchEvent  MotionEvent.ACTION_MOVE");
//                if (Math.abs((evX - startX) / (evY - startY)) > 3 && (evX - startX) > 100) {
//                    Log.d(TAG, "onTouchEvent() called with: event = [" + event + "]");
//                    System.out.println("滑动退出 = " + evY);
//                    activity.finish();
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                System.out.println("SwipeExitLayout.onTouchEvent  MotionEvent.ACTION_UP");
//                break;
//        }
        return super.onTouchEvent(event);
    }

    private static final String TAG = "SwipeExitLayout";
}
