package cn.zhengjun.androiddevelopmentadvanced.chapter02view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2018/5/4
 * Summary : 在这里描述Class的主要功能
 */

public final class SwipeExitTextView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "SwipeExitTextView";
    public SwipeExitTextView(Context context) {
        super(context);
    }

    public SwipeExitTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("SwipeExitTextView.dispatchTouchEvent  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("SwipeExitTextView.dispatchTouchEvent  ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("SwipeExitTextView.dispatchTouchEvent  ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("SwipeExitTextView.dispatchTouchEvent  ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("SwipeExitTextView.onTouchEvent  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("SwipeExitTextView.onTouchEvent  ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("SwipeExitTextView.onTouchEvent  ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("SwipeExitTextView.onTouchEvent  ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }
}
