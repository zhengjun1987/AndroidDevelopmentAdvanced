package cn.zhengjun.androiddevelopmentadvanced.chapter02view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2017/11/21
 * Summary : 在这里描述Class的主要功能
 */

public class CustomView extends View {

    private float startX;
    private float startY;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = eventX;
                startY = eventY;
                System.out.println("MotionEvent.ACTION_DOWN  startX = " + startX + " startY = "+startY);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (eventX - startX);
                int deltaY = (int) (eventY - startY);
                System.out.println("MotionEvent.ACTION_MOVE  " +"  eventX = " + eventX + "  eventY = " + eventY + " deltaX = " + deltaX +"  deltaY = " + deltaY);
                layout(getLeft() + deltaX,getTop()+deltaY,getRight()+deltaX,getBottom()+deltaY);
//                startX = eventX;
//                startY = eventY;
                break;
            case MotionEvent.ACTION_UP:
                
                break;
        }
        return true;
    }
}
