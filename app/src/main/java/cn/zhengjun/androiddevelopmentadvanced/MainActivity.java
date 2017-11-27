package cn.zhengjun.androiddevelopmentadvanced;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zhengjun.androiddevelopmentadvanced.chapter02view.CustomViewTestActivity;
import cn.zhengjun.androiddevelopmentadvanced.chapter08reativex.Test;

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
                        Test.repeat();
                    }
                }).show();
            }
        });
    }
}
