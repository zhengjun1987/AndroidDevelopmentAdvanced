package cn.zhengjun.androiddevelopmentadvanced.chapter08reativex;

import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;
import rx.schedulers.TimeInterval;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/12/1 9:53
 * Summary : null
 */

public class MergeTest {
    public static void test() {
        Observable.merge(Observable.interval(1,TimeUnit.SECONDS).take(5).delay(3,TimeUnit.SECONDS),Observable.interval(2, TimeUnit.SECONDS).take(4))
                .subscribe(new Subscriber<Serializable>() {

                    private long currentTimeMillis;

                    @Override
                    public void onStart() {
                        currentTimeMillis = System.currentTimeMillis();
                        System.out.println("MergeTest.onStart");
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("MergeTest.onCompleted时间间隔:"+(System.currentTimeMillis() - currentTimeMillis));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Serializable serializable) {
                        System.out.println("serializable = [" + serializable + "]  时间间隔:"+(System.currentTimeMillis() - currentTimeMillis));
                    }
                });
        // TODO: 2017/12/1 concat关键字的效果
//        12-01 10:30:23.558 8470-8470/cn.zhengjun.androiddevelopmentadvanced I/System.out: MergeTest.onStart
//        12-01 10:30:27.568 8470-8693/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [0]  时间间隔:4014
//        12-01 10:30:28.568 8470-8693/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [1]  时间间隔:5013
//        12-01 10:30:29.568 8470-8693/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [2]  时间间隔:6012
//        12-01 10:30:30.568 8470-8693/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [3]  时间间隔:7013
//        12-01 10:30:31.568 8470-8693/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [4]  时间间隔:8012
//        12-01 10:30:33.568 8470-8798/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [0]  时间间隔:10013
//        12-01 10:30:35.568 8470-8798/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [1]  时间间隔:12013
//        12-01 10:30:37.568 8470-8798/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [2]  时间间隔:14013
//        12-01 10:30:39.568 8470-8798/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [3]  时间间隔:16013
//        12-01 10:30:39.568 8470-8798/cn.zhengjun.androiddevelopmentadvanced I/System.out: MergeTest.onCompleted时间间隔:16014

        // TODO: 2017/12/1 merge关键字的效果
//        12-01 10:39:39.728 17250-17250/cn.zhengjun.androiddevelopmentadvanced I/System.out: MergeTest.onStart
//        12-01 10:39:41.738 17250-17340/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [0]  时间间隔:2015
//        12-01 10:39:43.738 17250-17355/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [0]  时间间隔:4015
//        12-01 10:39:43.738 17250-17340/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [1]  时间间隔:4016
//        12-01 10:39:44.738 17250-17355/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [1]  时间间隔:5013
//        12-01 10:39:45.738 17250-17355/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [2]  时间间隔:6013
//        12-01 10:39:45.738 17250-17340/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [2]  时间间隔:6015
//        12-01 10:39:46.738 17250-17355/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [3]  时间间隔:7014
//        12-01 10:39:47.738 17250-17355/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [4]  时间间隔:8014
//        12-01 10:39:47.738 17250-17340/cn.zhengjun.androiddevelopmentadvanced I/System.out: serializable = [3]  时间间隔:8015
//        12-01 10:39:47.738 17250-17340/cn.zhengjun.androiddevelopmentadvanced I/System.out: MergeTest.onCompleted时间间隔:8015
    }

    public static void zip(){
        Observable.zip(Observable.just("hello"), Observable.just("rxjava","rxandroid").delay(3,TimeUnit.SECONDS), new Func2<String, String, String>() {
            @Override
            public String call(String s, String aLong) {
                System.out.println("s = [" + s + "], aLong = [" + aLong + "]");
                return s+","+aLong;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                System.out.println("MergeTest.onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("MergeTest.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(String s) {
                System.out.println("s = [" + s + "]");
            }
        });
//        12-01 16:48:42.274 17991-17991/cn.zhengjun.androiddevelopmentadvanced I/System.out: MergeTest.onStart
//        12-01 16:48:45.285 17991-18207/cn.zhengjun.androiddevelopmentadvanced I/System.out: s = [hello], aLong = [rxjava]
//        12-01 16:48:45.286 17991-18207/cn.zhengjun.androiddevelopmentadvanced I/System.out: s = [hello,rxjava]
//        12-01 16:48:45.286 17991-18207/cn.zhengjun.androiddevelopmentadvanced I/System.out: MergeTest.onCompleted
    }

    private static final String TAG = "MergeTest";

    public static void intervalVar(){
        Logger.i(TAG, "MergeTest.intervalVar  " + "");
        Observable.zip(Observable.range(100, 10), Observable.interval(2, TimeUnit.SECONDS).take(10), new Func2<Integer, Long, String>() {
            @Override
            public String call(Integer integer, Long aLong) {
                return integer+" "+aLong;
            }
        }).timeInterval().subscribe(new Subscriber<TimeInterval<String>>() {
            @Override
            public void onStart() {
                System.out.println("MergeTest.onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("MergeTest.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("MergeTest.onError  " + "e = [" + e + "]");
            }

            @Override
            public void onNext(TimeInterval<String> stringTimeInterval) {
                System.out.println("MergeTest.onNext  " + "stringTimeInterval = [" + stringTimeInterval + "]");
            }
        });
//        12-02 16:04:04.371 9065-9065/cn.zhengjun.androiddevelopmentadvanced I/System.out: MergeTest.onStart
//        12-02 16:04:06.381 9065-9432/cn.zhengjun.androiddevelopmentadvanced I/System.out: stringTimeInterval = [TimeInterval [intervalInMilliseconds=2010, value=100 0]]
//        12-02 16:04:08.381 9065-9432/cn.zhengjun.androiddevelopmentadvanced I/System.out: stringTimeInterval = [TimeInterval [intervalInMilliseconds=2000, value=101 1]]
//        12-02 16:04:10.381 9065-9432/cn.zhengjun.androiddevelopmentadvanced I/System.out: stringTimeInterval = [TimeInterval [intervalInMilliseconds=2000, value=102 2]]
//        12-02 16:04:12.381 9065-9432/cn.zhengjun.androiddevelopmentadvanced I/System.out: stringTimeInterval = [TimeInterval [intervalInMilliseconds=2000, value=103 3]]
//        12-02 16:04:14.381 9065-9432/cn.zhengjun.androiddevelopmentadvanced I/System.out: stringTimeInterval = [TimeInterval [intervalInMilliseconds=2000, value=104 4]]
//        12-02 16:04:16.381 9065-9432/cn.zhengjun.androiddevelopmentadvanced I/System.out: stringTimeInterval = [TimeInterval [intervalInMilliseconds=1999, value=105 5]]
//        12-02 16:04:18.381 9065-9432/cn.zhengjun.androiddevelopmentadvanced I/System.out: stringTimeInterval = [TimeInterval [intervalInMilliseconds=2001, value=106 6]]
//        12-02 16:04:20.381 9065-9432/cn.zhengjun.androiddevelopmentadvanced I/System.out: stringTimeInterval = [TimeInterval [intervalInMilliseconds=1999, value=107 7]]
//        12-02 16:04:22.381 9065-9432/cn.zhengjun.androiddevelopmentadvanced I/System.out: stringTimeInterval = [TimeInterval [intervalInMilliseconds=2000, value=108 8]]
//        12-02 16:04:24.381 9065-9432/cn.zhengjun.androiddevelopmentadvanced I/System.out: stringTimeInterval = [TimeInterval [intervalInMilliseconds=2001, value=109 9]]
//        12-02 16:04:24.381 9065-9432/cn.zhengjun.androiddevelopmentadvanced I/System.out: MergeTest.onCompleted
    }
}
