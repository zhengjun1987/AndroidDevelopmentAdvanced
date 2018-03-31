package cn.zhengjun.androiddevelopmentadvanced.chapter02view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zhengjun.androiddevelopmentadvanced.R;

public class ViewAnimationActivity extends AppCompatActivity {

    @BindView(R.id.iv_img)
    ImageView ivImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        ButterKnife.bind(this);

        //属性动画
        ObjectAnimator.ofFloat(ivImg,"translationX",0,100);

//        Animator animator = AnimatorInflater.loadAnimator(this, R.anim.tran);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 100, 100);
        animation.setDuration(100);
        animation.setInterpolator(new LinearInterpolator());
        ivImg.setAnimation(animation);
    }
}
