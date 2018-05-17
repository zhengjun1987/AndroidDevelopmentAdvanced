package cn.zhengjun.androiddevelopmentadvanced.chapter04multithreads;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zhengjun.androiddevelopmentadvanced.R;

public class HandlerThreadActivity extends AppCompatActivity {

    public static final int MSG_UPDATE_INFO = 666;
    @BindView(R.id.tv_text)
    TextView tvText;
    private HandlerThread handlerThread;
    private Handler handler;
    private boolean isUpdateInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        ButterKnife.bind(this);

        initThread();
    }

    private void initThread() {
        handlerThread = new HandlerThread("handlerThread");

        handlerThread.start();

        handler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println("Handler.handleMessage  " + "msg = [" + msg + "] " + "Thread.currentThread() = [" + Thread.currentThread() + "]");
                update();
                if (isUpdateInfo){
                    handler.sendEmptyMessage(MSG_UPDATE_INFO);
                }
            }
        };

    }

    private void update() {
        System.out.println("HandlerThreadActivity.update  " + "");
        try {
            Thread.sleep(1000);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    System.out.println("update Runnable.run "+ "Thread.currentThread() = [" + Thread.currentThread() + "]");
                    String s = "每一秒更新一下数据 ";
                    s += Double.toString(Math.random());
                    tvText.setText(s);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isUpdateInfo = true;
        handler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isUpdateInfo = false;
        handler.removeMessages(MSG_UPDATE_INFO);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }
}
