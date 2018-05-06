package cn.zhengjun.androiddevelopmentadvanced.chapter02view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zhengjun.androiddevelopmentadvanced.R;

public class SwipeExitActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    @BindView(R.id.tv_inner)
    SwipeExitTextView tvInner;
    @BindView(R.id.parentPanel)
    SwipeExitLayout parentPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_exit);
        ButterKnife.bind(this);
        parentPanel.setActivity(this);
        tvInner.setOnClickListener(this);
        tvInner.setOnTouchListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        System.out.println("===============开始分发===================");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("SwipeExitActivity.dispatchTouchEvent  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("SwipeExitActivity.dispatchTouchEvent  ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("SwipeExitActivity.dispatchTouchEvent  ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("SwipeExitActivity.dispatchTouchEvent  ACTION_CANCEL");
                break;
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(event);
        System.out.println("===============结束分发===================dispatchTouchEvent 返回 " + dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("SwipeExitActivity.onTouchEvent  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("SwipeExitActivity.onTouchEvent  ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("SwipeExitActivity.onTouchEvent  ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("SwipeExitActivity.onTouchEvent  ACTION_CANCEL");
                break;
        }
        boolean onTouchEvent = super.onTouchEvent(event);
        System.out.println("SwipeExitActivity.onTouchEvent 返回 " + onTouchEvent);
        return onTouchEvent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_inner:
                System.out.println("OnClickListener.onClick");
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.tv_inner:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("OnTouchListener.onTouch  ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("OnTouchListener.onTouch  ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("OnTouchListener.onTouch  ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        System.out.println("OnTouchListener.onTouch  ACTION_CANCEL");
                        break;
                }
                break;
        }
        return false;
    }
}
