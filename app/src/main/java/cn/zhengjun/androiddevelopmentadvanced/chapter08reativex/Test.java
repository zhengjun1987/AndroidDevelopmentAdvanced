package cn.zhengjun.androiddevelopmentadvanced.chapter08reativex;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/11/27 14:29
 * Summary : null
 */

public class Test {
    public static void hello() {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                System.out.println("Subscriber.onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("Subscriber.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Subscriber.onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber.onNext  s = [" + s + "]");
            }
        };

        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("天高地迥，觉宇宙之无穷");
                subscriber.onNext("兴尽悲来，识盈虚之有数");
                subscriber.onCompleted();
            }
        });

        stringObservable.subscribe(subscriber);
//        Subscriber.onStart
//        Subscriber.onNext  s = [天高地迥，觉宇宙之无穷]
//        Subscriber.onNext  s = [兴尽悲来，识盈虚之有数]
//        Subscriber.onCompleted

        System.out.println();

        Observable<String> just = Observable.just("翩若惊鸿，婉若游龙。荣曜秋菊，华茂春松。", "仿佛兮若轻云之蔽月，飘摇兮若流风之回雪。");
        just.subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                System.out.println("Subscriber.onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("Subscriber.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Subscriber.onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber.onNext  s = [" + s + "]");
            }
        });

        System.out.println();

        String[] strings = {"红酥手。黄滕酒。满城春色宫墙柳。","东风恶。欢情薄。一怀愁绪，几年离索。","错错错。春如旧。人空瘦。泪痕红浥鲛绡透。","桃花落。闲池阁。山盟虽在，锦书难托。莫莫莫。"};
        Observable<String> from = Observable.from(strings);
        from.subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                System.out.println("Subscriber.onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("Subscriber.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Subscriber.onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber.onNext  s = [" + s + "]");
            }
        });
//        Subscriber.onStart
//        Subscriber.onNext  s = [天高地迥，觉宇宙之无穷]
//        Subscriber.onNext  s = [兴尽悲来，识盈虚之有数]
//        Subscriber.onCompleted
//
//        Subscriber.onStart
//        Subscriber.onNext  s = [翩若惊鸿，婉若游龙。荣曜秋菊，华茂春松。]
//        Subscriber.onNext  s = [仿佛兮若轻云之蔽月，飘摇兮若流风之回雪。]
//        Subscriber.onCompleted
//
//        Subscriber.onStart
//        Subscriber.onNext  s = [红酥手。黄滕酒。满城春色宫墙柳。]
//        Subscriber.onNext  s = [东风恶。欢情薄。一怀愁绪，几年离索。]
//        Subscriber.onNext  s = [错错错。春如旧。人空瘦。泪痕红浥鲛绡透。]
//        Subscriber.onNext  s = [桃花落。闲池阁。山盟虽在，锦书难托。莫莫莫。]
//        Subscriber.onCompleted
    }

    public static void fragmentary() {
        Observable.zip(Observable.just("fragmentary","腾蛟起凤","紫电青霜"),Observable.just("哀吾生之须臾","羡长江之无穷"),new Func2<String, String, String>(){

            @Override
            public String call(String s, String s2) {
                System.out.println("s = [" + s + "], s2 = [" + s2 + "]");
                return s + "  "+s2;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Test.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Test.onError:"+e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("s = [" + s + "]");
            }
        });
//        s = [fragmentary], s2 = [哀吾生之须臾]
//        s = [fragmentary  哀吾生之须臾]
//        s = [腾蛟起凤], s2 = [羡长江之无穷]
//        s = [腾蛟起凤  羡长江之无穷]
//        Test.onCompleted

        Observable.just("fragmentary","腾蛟起凤","紫电青霜").subscribe(
                new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("Test.onNext:" + s);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("Test.onError:" + throwable.getMessage());
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("Test.onCompleted");
                    }
                }
        );
//    Test.onNext:fragmentary
//    Test.onNext:腾蛟起凤
//    Test.onNext:紫电青霜
//    Test.onCompleted
    }

    public static void interval() {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
            public void onCompleted() {
                System.out.println("Test.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e.getMessage() + "]");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("aLong = [" + aLong + "]");
            }
        });
    }

    public static void range() {
        Observable.range(0,20,Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Test.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Test.onNext:"+integer);
            }
        });
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:0
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:1
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:2
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:3
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:4
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:5
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:6
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:7
//        11-27 15:52:30.370 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:8
//        11-27 15:52:30.370 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:9
//        11-27 15:52:30.370 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:10
//        11-27 15:52:30.370 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:11
//        11-27 15:52:30.370 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:12
//        11-27 15:52:30.371 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:13
//        11-27 15:52:30.371 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:14
//        11-27 15:52:30.371 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:15
//        11-27 15:52:30.372 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:16
//        11-27 15:52:30.372 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:17
//        11-27 15:52:30.372 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:18
//        11-27 15:52:30.372 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onNext:19
//        11-27 15:52:30.372 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onCompleted
    }

    public static void repeat() {
        Observable.range(0,5).repeat(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("throwable = [" + throwable + "]");
            }
        }, new Action0() {
            @Override
            public void call() {
                System.out.println("Test.onCompleted!");
            }
        });
//        11-27 15:57:23.764 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [0]
//        11-27 15:57:23.764 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [1]
//        11-27 15:57:23.764 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [2]
//        11-27 15:57:23.764 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [3]
//        11-27 15:57:23.764 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [4]
//        11-27 15:57:23.766 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [0]
//        11-27 15:57:23.766 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [1]
//        11-27 15:57:23.766 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [2]
//        11-27 15:57:23.766 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [3]
//        11-27 15:57:23.766 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: integer = [4]
//        11-27 15:57:23.767 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: Test.onCompleted!
    }

    public static void start(){}

    public static void defer(){}

    public static void timer(){}

    public static final String HOST = "http://blog.csdn.net/";

    public static void map(){
        Observable.just("itachi85","zhengjun1987").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return HOST+s;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
                System.out.println("Test.onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("Test.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e.getMessage() + "]");
            }

            @Override
            public void onNext(String s) {
                System.out.println("s = [" + s + "]");
            }
        });
//        Test.onStart
//        s = [http://blog.csdn.net/itachi85]
//        s = [http://blog.csdn.net/zhengjun1987]
//        Test.onCompleted
    }
}
