package cn.zhengjun.androiddevelopmentadvanced.chapter01toucheventdispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/11/16
 * Summary : 在这里描述Class的主要功能
 */

public class MyTextView extends android.support.v7.widget.AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("MyTextView.dispatchTouchEvent  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("MyTextView.dispatchTouchEvent  ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("MyTextView.dispatchTouchEvent  ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("MyTextView.dispatchTouchEvent  ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("MyTextView.onTouchEvent  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("MyTextView.onTouchEvent  ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("MyTextView.onTouchEvent  ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("MyTextView.onTouchEvent  ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }
}
