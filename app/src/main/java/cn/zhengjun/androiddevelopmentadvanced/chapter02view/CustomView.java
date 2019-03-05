package cn.zhengjun.androiddevelopmentadvanced.chapter02view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Scroller;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2017/11/21
 * Summary : 在这里描述Class的主要功能
 */

@SuppressLint("AppCompatCustomView")
public class CustomView extends ImageView {

    private float startX;
    private float startY;
    private Scroller scroller;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
    }

    private void smoothScrollTo(int destX,int destY){
        int scrollX = getScrollX();
        int deltaX = destX - scrollX;
        scroller.startScroll(scrollX,0,deltaX,0,1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = eventX;
                startY = eventY;
                System.out.println("MotionEvent.ACTION_DOWN  startX = " + startX + " startY = " + startY);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (eventX - startX);
                int deltaY = (int) (eventY - startY);
                System.out.println("MotionEvent.ACTION_MOVE  " + "  eventX = " + eventX + "  eventY = " + eventY + " deltaX = " + deltaX + "  deltaY = " + deltaY);
//                System.out.println("getHeight() = " + getHeight() + "  getMeasuredHeight() = " + getMeasuredHeight());

//                layout(getLeft() + deltaX,getTop()+deltaY,getRight()+deltaX,getBottom()+deltaY);
                scrollTo(-(int) eventX,-(int) eventY);
//                scrollBy(-deltaX,-deltaY);
                System.out.print("getScrollX() = " + getScrollX());
                System.out.println("  getScrollY() = " + getScrollY());
                startX = eventX;
                startY = eventY;
                break;
            case MotionEvent.ACTION_UP:

                break;
        }

//        boolean onTouchEvent = gestureDetector.onTouchEvent(event);
        return true;
    }


}
