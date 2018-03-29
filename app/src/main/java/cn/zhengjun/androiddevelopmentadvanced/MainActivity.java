package cn.zhengjun.androiddevelopmentadvanced;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zhengjun.androiddevelopmentadvanced.chapter02view.CustomViewTestActivity;
import cn.zhengjun.androiddevelopmentadvanced.chapter08reativex.MergeTest;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    TextView button1;
    @BindView(R.id.button2)
    TextView button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter());
        ButterKnife.bind(this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomViewTestActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(button2,"操作成功",Snackbar.LENGTH_INDEFINITE).setAction("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MergeTest.intervalVar();
                    }
                }).show();
            }
        });

        new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                System.out.println("GestureDetector.onDown  " + "e = [" + e + "]");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                System.out.println("GestureDetector.onShowPress  " + "e = [" + e + "]");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                System.out.println("GestureDetector.onSingleTapUp  " + "e = [" + e + "]");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                System.out.println("GestureDetector.onScroll  " + "e1 = [" + e1 + "], e2 = [" + e2 + "], distanceX = [" + distanceX + "], distanceY = [" + distanceY + "]");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                System.out.println("GestureDetector.onLongPress  " + "e = [" + e + "]");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                System.out.println("GestureDetector.onFling  " + "e1 = [" + e1 + "], e2 = [" + e2 + "], velocityX = [" + velocityX + "], velocityY = [" + velocityY + "]");
                return false;
            }
        });
    }

    private static final String TAG = "MainActivity";
}
