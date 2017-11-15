package cn.zhengjun.androiddevelopmentadvanced.chapter01toucheventdispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2017/11/16
 * Summary : 在这里描述Class的主要功能
 */

public class MyRelativeLayout extends RelativeLayout {
    private static final String TAG = "MyRelativeLayout";
    
    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
