package cn.zhengjun.androiddevelopmentadvanced.chapter02view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zhengjun.androiddevelopmentadvanced.R;

public class CustomViewTestActivity extends AppCompatActivity {

    @BindView(R.id.cv_button)
    CustomView cvButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_test);
        ButterKnife.bind(this);
        cvButton.setImageResource(R.drawable.zjz);
        cvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(cvButton,"SnackBar测试",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
