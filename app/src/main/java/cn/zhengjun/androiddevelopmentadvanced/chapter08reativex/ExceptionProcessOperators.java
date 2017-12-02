package cn.zhengjun.androiddevelopmentadvanced.chapter08reativex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/11/29 11:41
 * Summary : null
 */

public class ExceptionProcessOperators {
    public static void catchErr() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 6; i++) {
                    if (i > 3) {
                        subscriber.onError(new Exception("Throwable"));
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        })
                .onErrorResumeNext(Observable.range(21, 5))
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        integer = [3]
//        integer = [21]
//        integer = [22]
//        integer = [23]
//        integer = [24]
//        integer = [25]
//        ExceptionProcessOperators.onCompleted
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Integer>>() {
                    @Override
                    public Observable<? extends Integer> call(Throwable throwable) {
                        return Observable.range(11, 6);
                    }
                })
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        integer = [3]
//        integer = [11]
//        integer = [12]
//        integer = [13]
//        integer = [14]
//        integer = [15]
//        integer = [16]
//        ExceptionProcessOperators.onCompleted
                .onErrorReturn(new Func1<Throwable, Integer>() {
                    @Override
                    public Integer call(Throwable throwable) {
                        return 9999;
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("ExceptionProcessOperators.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        integer = [3]
//        integer = [9999]
//        ExceptionProcessOperators.onCompleted
    }

    public static void retry() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 6; i++) {
                    if (i == 3) {
                        subscriber.onError(new Exception("Exception"));
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).retry(3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("ExceptionProcessOperators.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        e = [java.lang.Exception: Exception]

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 6; i++) {
                    if (i == 3) {
                        subscriber.onError(new Exception("Exception"));
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).retry(new Func2<Integer, Throwable, Boolean>() {
            @Override
            public Boolean call(Integer integer, Throwable throwable) {
                System.out.println("Func2.call  integer = [" + integer + "], throwable = [" + throwable + "]");
                return throwable.getMessage().equals("Exception");
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("ExceptionProcessOperators.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        Func2.call  integer = [620043], throwable = [java.lang.Exception: Exception]
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        Func2.call  integer = [620044], throwable = [java.lang.Exception: Exception]
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        Func2.call  integer = [620045], throwable = [java.lang.Exception: Exception]
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        Func2.call  integer = [620046], throwable = [java.lang.Exception: Exception]
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        Func2.call  integer = [620047], throwable = [java.lang.Exception: Exception]
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        Func2.call  integer = [620048], throwable = [java.lang.Exception: Exception]
//        ......
    }

    private static int count = 0;

    public static void retryWhen() {

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 6; i++) {
                    if (i == 3) {
                        subscriber.onError(new Exception("3"));
                    }
//                    if (i == 4) {
//                        subscriber.onError(new Exception("4"));
//                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {

                return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        System.out.println("throwable = " + throwable + "  count = " + count);
                        if (throwable.getMessage().equals("3") && count < 3) {
                            count++;
                            return Observable.timer(1500, TimeUnit.MILLISECONDS);
                        } else {
                            count = 0;
                            return Observable.error(new Exception("重试3次之后仍错误!!!"));
                        }
                    }
                });
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onStart() {
                super.onStart();
                System.out.println("ExceptionProcessOperators.onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("ExceptionProcessOperators.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
//        11-29 14:58:01.947 19414-19414/cn.zhengjun.androiddevelopmentadvanced I/System.out: ExceptionProcessOperators.onStart
//        11-29 14:58:01.951 19414-19414/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [0]
//        11-29 14:58:01.951 19414-19414/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [1]
//        11-29 14:58:01.951 19414-19414/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [2]
//        11-29 14:58:01.952 19414-19414/cn.zhengjun.androiddevelopmentadvanced I/System.out: throwable = java.lang.Exception: 3  count = 0
//        11-29 14:58:02.155 19414-19449/cn.zhengjun.androiddevelopmentadvanced W/OpenGLRenderer: cuilf pushLayerUpdate 309 delete mLayer: 0x7f91735500, RenderLayer: 0x7f92882800
//        11-29 14:58:03.467 19414-19519/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [0]
//        11-29 14:58:03.468 19414-19519/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [1]
//        11-29 14:58:03.468 19414-19519/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [2]
//        11-29 14:58:03.470 19414-19519/cn.zhengjun.androiddevelopmentadvanced I/System.out: throwable = java.lang.Exception: 3  count = 1
//        11-29 14:58:04.975 19414-19541/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [0]
//        11-29 14:58:04.976 19414-19541/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [1]
//        11-29 14:58:04.976 19414-19541/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [2]
//        11-29 14:58:04.977 19414-19541/cn.zhengjun.androiddevelopmentadvanced I/System.out: throwable = java.lang.Exception: 3  count = 2
//        11-29 14:58:06.482 19414-19571/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [0]
//        11-29 14:58:06.483 19414-19571/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [1]
//        11-29 14:58:06.483 19414-19571/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [2]
//        11-29 14:58:06.484 19414-19571/cn.zhengjun.androiddevelopmentadvanced I/System.out: throwable = java.lang.Exception: 3  count = 3
//        11-29 14:58:06.487 19414-19571/cn.zhengjun.androiddevelopmentadvanced I/System.out: e = [java.lang.Exception: 重试3次之后仍错误!!!]
    }

    public static void all() {
        Observable.range(0, 5)
                .all(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer < 4;
                    }
                }).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                System.out.println("ExceptionProcessOperators.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Boolean aBoolean) {
                System.out.println("aBoolean = [" + aBoolean + "]");
            }
        });
//        aBoolean = [false]
//        ExceptionProcessOperators.onCompleted
    }

    public static void contains() {
        Observable.range(0, 5).contains(4).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println("aBoolean = [" + aBoolean + "]");
            }
        });
//        aBoolean = [true]
        Observable.range(0, 5).isEmpty().subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println("aBoolean = [" + aBoolean + "]");
            }
        });
//        aBoolean = [false]
    }

    public static void amb(){
        Observable.amb(Observable.range(0,3).delay(1,TimeUnit.SECONDS),Observable.just(3,4,5))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onStart() {
                        System.out.println("ExceptionProcessOperators.onStart");
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("ExceptionProcessOperators.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("ExceptionProcessOperators.onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });
//        ExceptionProcessOperators.onStart
//        integer = [3]
//        integer = [4]
//        integer = [5]
//        ExceptionProcessOperators.onCompleted
    }

    public static void defaultEmpty(){
        Observable.range(0,10).ignoreElements().defaultIfEmpty(9999)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onStart() {
                        System.out.println("ExceptionProcessOperators.onStart");
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("ExceptionProcessOperators.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });
//        ExceptionProcessOperators.onStart
//        integer = [9999]
//        ExceptionProcessOperators.onCompleted
    }

    public static void toList(){
        Observable.just(0, 1, 9, 3, 4, 5, 6, 7, 8, 11).toList().subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("integers = [" + integers + "]");
            }
        });
//        integers = [[0, 1, 9, 3, 4, 5, 6, 7, 8, 11]]
    }

    public static void toSortedList(){
        Observable.just(0, 1, 9, 3, 4, 5, 6, 7, 8, 11).toSortedList().subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("integers = [" + integers + "]");
            }
        });
//        integers = [[0, 1, 3, 4, 5, 6, 7, 8, 9, 11]]
    }

    public static void toMap(){
        ArrayList<SwordMan> swordMen = new ArrayList<>();
        swordMen.add(new SwordMan(1, "段正淳"));
        swordMen.add(new SwordMan(1, "鸠摩智"));
        swordMen.add(new SwordMan(0, "张三丰"));
        swordMen.add(new SwordMan(0, "张无忌"));
        swordMen.add(new SwordMan(2, "灵鹫宫"));
        swordMen.add(new SwordMan(0, "萧峰"));
        swordMen.add(new SwordMan(0, "扫地僧"));
        swordMen.add(new SwordMan(1, "慕容复"));
        swordMen.add(new SwordMan(2, "丁春秋"));
        Observable.from(swordMen).toMap(new Func1<SwordMan, String>() {
            @Override
            public String call(SwordMan swordMan) {
                return swordMan.getName();
            }
        }).subscribe(new Action1<Map<String, SwordMan>>() {
            @Override
            public void call(Map<String, SwordMan> integerSwordManMap) {
                for (String s : integerSwordManMap.keySet()) {
                    System.out.println("key = " + s +" ; value = "+integerSwordManMap.get(s));
                }
            }
        });
//        key = 慕容复 ; value = SwordMan{level=1, name='慕容复'}
//        key = 段正淳 ; value = SwordMan{level=1, name='段正淳'}
//        key = 丁春秋 ; value = SwordMan{level=2, name='丁春秋'}
//        key = 张三丰 ; value = SwordMan{level=0, name='张三丰'}
//        key = 萧峰 ; value = SwordMan{level=0, name='萧峰'}
//        key = 灵鹫宫 ; value = SwordMan{level=2, name='灵鹫宫'}
//        key = 张无忌 ; value = SwordMan{level=0, name='张无忌'}
//        key = 扫地僧 ; value = SwordMan{level=0, name='扫地僧'}
//        key = 鸠摩智 ; value = SwordMan{level=1, name='鸠摩智'}
    }

    public static void timeout(){
        Observable.range(11,5).delay(3,TimeUnit.SECONDS)
                .timeout(4,TimeUnit.SECONDS)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onStart() {
                        System.out.println("ExceptionProcessOperators.onStart");
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("ExceptionProcessOperators.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        TimeoutException timeoutException = e instanceof TimeoutException ? ((TimeoutException) e) : null;
                        if (timeoutException != null) {
                            System.out.println("操作超时未返回数据!");
                        }
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });
//        12-02 14:54:03.811 12881-12881/cn.zhengjun.androiddevelopmentadvanced I/System.out: ExceptionProcessOperators.onStart
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err: java.util.concurrent.TimeoutException
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err:     at rx.internal.operators.OperatorTimeoutBase$TimeoutSubscriber.onTimeout(OperatorTimeoutBase.java:177)
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err:     at rx.internal.operators.OperatorTimeout$1$1.call(OperatorTimeout.java:41)
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err:     at rx.internal.schedulers.EventLoopsScheduler$EventLoopWorker$2.call(EventLoopsScheduler.java:189)
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err:     at rx.internal.schedulers.ScheduledAction.run(ScheduledAction.java:55)
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err:     at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:423)
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err:     at java.util.concurrent.FutureTask.run(FutureTask.java:237)
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err:     at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$201(ScheduledThreadPoolExecutor.java:154)
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err:     at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:269)
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err:     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1113)
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err:     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:588)
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced W/System.err:     at java.lang.Thread.run(Thread.java:818)
//        12-02 14:54:07.821 12881-13362/cn.zhengjun.androiddevelopmentadvanced I/System.out: 操作超时未返回数据!
    }
}
