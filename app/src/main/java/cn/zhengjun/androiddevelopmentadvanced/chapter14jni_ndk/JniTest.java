package cn.zhengjun.androiddevelopmentadvanced.chapter14jni_ndk;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2018/5/7
 * Summary : 在这里描述Class的主要功能
 */

public class JniTest {
    static {
        System.loadLibrary("jni-test");
    }
    public native String get();
    public native void set(String s);
    public static void main(String[] args) {
        JniTest jniTest = new JniTest();
        System.out.println("jniTest.get() = " + jniTest.get());
        jniTest.set("zhengjun1987@outlook.com");
        System.out.println("jniTest.get() = " + jniTest.get());
    }

}
