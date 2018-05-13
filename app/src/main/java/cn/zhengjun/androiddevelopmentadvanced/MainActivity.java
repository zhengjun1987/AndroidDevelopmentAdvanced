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

import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zhengjun.androiddevelopmentadvanced.chapter02view.CustomViewTestActivity;
import cn.zhengjun.androiddevelopmentadvanced.chapter02view.SwipeExitActivity;
import cn.zhengjun.androiddevelopmentadvanced.chapter08reativex.GetRequestInterface;
import cn.zhengjun.androiddevelopmentadvanced.chapter08reativex.MergeTest;
import cn.zhengjun.androiddevelopmentadvanced.chapter08reativex.Translation;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    TextView button1;
    @BindView(R.id.button2)
    TextView button2;
    @BindView(R.id.button3)
    TextView button3;
    @BindView(R.id.button4)
    TextView button4;

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
                Snackbar.make(button2, "操作成功", Snackbar.LENGTH_INDEFINITE).setAction("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MergeTest.intervalVar();
                    }
                }).show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SwipeExitActivity.class));
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Executors.newCachedThreadPool().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        request();
//                    }
//                });

                request();
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

    private void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GetRequestInterface getRequestInterface = retrofit.create(GetRequestInterface.class);
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("getRequestInterface.getClass().getCanonicalName() = " + getRequestInterface.getClass().toString());
        final Observable<Translation> translationCall = getRequestInterface.getCall();
//        translationCall.enqueue(new Callback<Translation>() {
//            @Override
//            public void onResponse(Call<Translation> call, Response<Translation> response) {
//                System.out.println("response = " + response);
//                System.out.println("response.message() = " + response.message());
//                Translation translation = response.body();
//                System.out.println("translation = " + translation);
//            }
//
//            @Override
//            public void onFailure(Call<Translation> call, Throwable t) {
//                System.out.println("(call.equals(translationCall)) = " + (call.equals(translationCall)));
//                System.out.println("请求失败 = " + t.getMessage());
//            }
//        });

        translationCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Translation>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("MainActivity.onCompleted  " + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("MainActivity.onError  " + "e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Translation translation) {
                        System.out.println("onNext  Thread.currentThread().getName() = " + Thread.currentThread().getName());
                        System.out.println("translation结果 = " + translation);
                    }
                });
    }

    private static final String TAG = "MainActivity";
}
