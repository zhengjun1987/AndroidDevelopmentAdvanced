package cn.zhengjun.androiddevelopmentadvanced;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.AndroidLogAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zhengjun.androiddevelopmentadvanced.chapter02view.CustomViewTestActivity;
import cn.zhengjun.androiddevelopmentadvanced.chapter02view.SwipeExitActivity;
import cn.zhengjun.androiddevelopmentadvanced.chapter04multithreads.HandlerThreadActivity;
import cn.zhengjun.androiddevelopmentadvanced.chapter05sql.DatabaseActivity;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.button1)
    TextView button1;
    @BindView(R.id.button2)
    TextView button2;
    @BindView(R.id.button3)
    TextView button3;
    @BindView(R.id.button4)
    TextView button4;
    @BindView(R.id.button5)
    TextView button5;
    @BindView(R.id.button6)
    TextView button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter());
        ButterKnife.bind(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, CustomViewTestActivity.class));
                break;
            case R.id.button2:
                Snackbar.make(button2, "操作成功", Snackbar.LENGTH_INDEFINITE).setAction("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MergeTest.intervalVar();
                    }
                }).show();
                break;
            case R.id.button3:
                startActivity(new Intent(MainActivity.this, SwipeExitActivity.class));
                break;
            case R.id.button4:
                request();
                break;
            case R.id.button5:
                startActivity(new Intent(MainActivity.this, HandlerThreadActivity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(MainActivity.this, DatabaseActivity.class));
                break;
        }
    }
}
