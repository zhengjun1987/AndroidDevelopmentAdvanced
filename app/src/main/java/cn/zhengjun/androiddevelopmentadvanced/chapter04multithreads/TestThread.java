package cn.zhengjun.androiddevelopmentadvanced.chapter04multithreads;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2018/5/5
 * Summary : 在这里描述Class的主要功能
 */

public class TestThread extends Thread {
    @Override
    public void run() {
        System.out.println("run  Thread.currentThread() = " + Thread.currentThread());
    }

    public static void main(String[] args) {
        System.out.println("main  Thread.currentThread() = " + Thread.currentThread());
        new TestThread().start();
    }
}
//        main  Thread.currentThread() = Thread[main,5,main]
//        run  Thread.currentThread() = Thread[Thread-0,5,main]
