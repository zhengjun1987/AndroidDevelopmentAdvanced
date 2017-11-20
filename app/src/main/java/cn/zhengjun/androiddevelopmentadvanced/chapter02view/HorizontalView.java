package cn.zhengjun.androiddevelopmentadvanced.chapter02view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2017/11/20
 * Summary : 在这里描述Class的主要功能
 */

public class HorizontalView extends ViewGroup {
    public HorizontalView(Context context) {
        super(context);
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
