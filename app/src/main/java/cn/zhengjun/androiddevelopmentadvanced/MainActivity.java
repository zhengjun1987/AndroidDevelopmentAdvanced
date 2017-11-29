package cn.zhengjun.androiddevelopmentadvanced;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zhengjun.androiddevelopmentadvanced.chapter02view.CustomViewTestActivity;
import cn.zhengjun.androiddevelopmentadvanced.chapter08reativex.IpRequest;
import cn.zhengjun.androiddevelopmentadvanced.chapter08reativex.OKHttpRepository;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    TextView button1;
    @BindView(R.id.button2)
    TextView button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        OKHttpRepository okHttpRepository = new OKHttpRepository();
                        okHttpRepository.requestAsync("115.204.105.219")
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<String>() {
                                    @Override
                                    public void onCompleted() {
                                        System.out.println("OKHttpRepositoryTest.onCompleted");
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        System.out.println("e = [" + e + "]");
                                    }

                                    @Override
                                    public void onNext(String s) {
                                        System.out.println("s = [" + s + "]");
                                        Gson gson = new Gson();
                                        IpRequest ipRequest = gson.fromJson(s, IpRequest.class);
                                        System.out.println("ipRequest = " + ipRequest);
                                    }
                                });
                    }
                }).show();
            }
        });
    }
}
