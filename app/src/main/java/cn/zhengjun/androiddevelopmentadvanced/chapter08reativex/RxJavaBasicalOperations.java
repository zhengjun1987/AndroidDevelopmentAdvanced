package cn.zhengjun.androiddevelopmentadvanced.chapter08reativex;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/11/27 14:29
 * Summary : null
 */

public class RxJavaBasicalOperations {
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
                System.out.println("RxJavaBasicalOperations.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("RxJavaBasicalOperations.onError:"+e.getMessage());
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
//        RxJavaBasicalOperations.onCompleted

        Observable.just("fragmentary","腾蛟起凤","紫电青霜").subscribe(
                new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("RxJavaBasicalOperations.onNext:" + s);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("RxJavaBasicalOperations.onError:" + throwable.getMessage());
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("RxJavaBasicalOperations.onCompleted");
                    }
                }
        );
//    RxJavaBasicalOperations.onNext:fragmentary
//    RxJavaBasicalOperations.onNext:腾蛟起凤
//    RxJavaBasicalOperations.onNext:紫电青霜
//    RxJavaBasicalOperations.onCompleted
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
                System.out.println("RxJavaBasicalOperations.onCompleted");
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
                System.out.println("RxJavaBasicalOperations.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("RxJavaBasicalOperations.onNext:"+integer);
            }
        });
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:0
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:1
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:2
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:3
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:4
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:5
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:6
//        11-27 15:52:30.369 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:7
//        11-27 15:52:30.370 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:8
//        11-27 15:52:30.370 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:9
//        11-27 15:52:30.370 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:10
//        11-27 15:52:30.370 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:11
//        11-27 15:52:30.370 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:12
//        11-27 15:52:30.371 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:13
//        11-27 15:52:30.371 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:14
//        11-27 15:52:30.371 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:15
//        11-27 15:52:30.372 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:16
//        11-27 15:52:30.372 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:17
//        11-27 15:52:30.372 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:18
//        11-27 15:52:30.372 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onNext:19
//        11-27 15:52:30.372 27583-27794/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onCompleted
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
                System.out.println("RxJavaBasicalOperations.onCompleted!");
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
//        11-27 15:57:23.767 5437-5437/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onCompleted!
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
                System.out.println("RxJavaBasicalOperations.onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("RxJavaBasicalOperations.onCompleted");
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
//        RxJavaBasicalOperations.onStart
//        s = [http://blog.csdn.net/itachi85]
//        s = [http://blog.csdn.net/zhengjun1987]
//        RxJavaBasicalOperations.onCompleted
    }

    public static void concatMap(){
        Observable.range(0,20).flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                return Observable.just(HOST+"itachi"+integer);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = " + s);
            }
        });
        System.out.println("-----------------------------------------");
        Observable.range(0,20).concatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                return Observable.just(HOST+"itachi"+integer);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = " + s);
            }
        });
//        s = http://blog.csdn.net/itachi0
//        s = http://blog.csdn.net/itachi1
//        s = http://blog.csdn.net/itachi2
//        s = http://blog.csdn.net/itachi3
//        s = http://blog.csdn.net/itachi4
//        s = http://blog.csdn.net/itachi5
//        s = http://blog.csdn.net/itachi6
//        s = http://blog.csdn.net/itachi7
//        s = http://blog.csdn.net/itachi8
//        s = http://blog.csdn.net/itachi9
//        s = http://blog.csdn.net/itachi10
//        s = http://blog.csdn.net/itachi11
//        s = http://blog.csdn.net/itachi12
//        s = http://blog.csdn.net/itachi13
//        s = http://blog.csdn.net/itachi14
//        s = http://blog.csdn.net/itachi15
//        s = http://blog.csdn.net/itachi16
//        s = http://blog.csdn.net/itachi17
//        s = http://blog.csdn.net/itachi18
//        s = http://blog.csdn.net/itachi19
//        -----------------------------------------
//        s = http://blog.csdn.net/itachi0
//        s = http://blog.csdn.net/itachi1
//        s = http://blog.csdn.net/itachi2
//        s = http://blog.csdn.net/itachi3
//        s = http://blog.csdn.net/itachi4
//        s = http://blog.csdn.net/itachi5
//        s = http://blog.csdn.net/itachi6
//        s = http://blog.csdn.net/itachi7
//        s = http://blog.csdn.net/itachi8
//        s = http://blog.csdn.net/itachi9
//        s = http://blog.csdn.net/itachi10
//        s = http://blog.csdn.net/itachi11
//        s = http://blog.csdn.net/itachi12
//        s = http://blog.csdn.net/itachi13
//        s = http://blog.csdn.net/itachi14
//        s = http://blog.csdn.net/itachi15
//        s = http://blog.csdn.net/itachi16
//        s = http://blog.csdn.net/itachi17
//        s = http://blog.csdn.net/itachi18
//        s = http://blog.csdn.net/itachi19
    }

    public static void flatMapIterable(){
        Observable.range(0,10).flatMapIterable(new Func1<Integer, Iterable<Integer>>() {
            private List<Integer> integers = new ArrayList<Integer>();
            @Override
            public Iterable<Integer> call(Integer integer) {
                integers.add(integer);
                return integers;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
//        integer = 0
//        integer = 0
//        integer = 1
//        integer = 0
//        integer = 1
//        integer = 2
//        integer = 0
//        integer = 1
//        integer = 2
//        integer = 3
//        integer = 0
//        integer = 1
//        integer = 2
//        integer = 3
//        integer = 4
//        integer = 0
//        integer = 1
//        integer = 2
//        integer = 3
//        integer = 4
//        integer = 5
//        integer = 0
//        integer = 1
//        integer = 2
//        integer = 3
//        integer = 4
//        integer = 5
//        integer = 6
//        integer = 0
//        integer = 1
//        integer = 2
//        integer = 3
//        integer = 4
//        integer = 5
//        integer = 6
//        integer = 7
//        integer = 0
//        integer = 1
//        integer = 2
//        integer = 3
//        integer = 4
//        integer = 5
//        integer = 6
//        integer = 7
//        integer = 8
//        integer = 0
//        integer = 1
//        integer = 2
//        integer = 3
//        integer = 4
//        integer = 5
//        integer = 6
//        integer = 7
//        integer = 8
//        integer = 9
        System.out.println();
        Observable.range(0,10).flatMapIterable(new Func1<Integer, Iterable<Integer>>() {
//            private List<Integer> integers = new ArrayList<Integer>();
            @Override
            public Iterable<Integer> call(Integer integer) {
                List<Integer> integers = new ArrayList<Integer>();
                integers.add(integer);
                return integers;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
//        integer = 0
//        integer = 1
//        integer = 2
//        integer = 3
//        integer = 4
//        integer = 5
//        integer = 6
//        integer = 7
//        integer = 8
//        integer = 9
    }

    public static void buffer(){
//        Observable.range(0,50).buffer(10)
//                .subscribe(new Action1<List<Integer>>() {
//                    @Override
//                    public void call(List<Integer> integers) {
//                        System.out.println("integers = " + integers);
//                    }
//                });
//        integers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
//        integers = [10, 11, 12, 13, 14, 15, 16, 17, 18, 19]
//        integers = [20, 21, 22, 23, 24, 25, 26, 27, 28, 29]
//        integers = [30, 31, 32, 33, 34, 35, 36, 37, 38, 39]
//        integers = [40, 41, 42, 43, 44, 45, 46, 47, 48, 49]
//        System.out.println();
        Observable.interval(1,TimeUnit.SECONDS)
                .buffer(10,TimeUnit.SECONDS)
                .subscribe(new Action1<List<Long>>() {
                    @Override
                    public void call(List<Long> longs) {
                        System.out.println("longs = [" + longs + "]");
                    }
                });
//        11-28 11:09:06.653 5232-5540/cn.zhengjun.androiddevelopmentadvanced I/System.out: longs = [[0, 1, 2, 3, 4, 5, 6, 7, 8]]
//        11-28 11:09:16.653 5232-5540/cn.zhengjun.androiddevelopmentadvanced I/System.out: longs = [[9, 10, 11, 12, 13, 14, 15, 16, 17, 18]]
//        11-28 11:09:26.652 5232-5540/cn.zhengjun.androiddevelopmentadvanced I/System.out: longs = [[19, 20, 21, 22, 23, 24, 25, 26, 27, 28]]
//        11-28 11:09:36.653 5232-5540/cn.zhengjun.androiddevelopmentadvanced I/System.out: longs = [[29, 30, 31, 32, 33, 34, 35, 36, 37, 38]]
//        11-28 11:09:46.652 5232-5540/cn.zhengjun.androiddevelopmentadvanced I/System.out: longs = [[39, 40, 41, 42, 43, 44, 45, 46, 47, 48]]
//        11-28 11:09:56.652 5232-5540/cn.zhengjun.androiddevelopmentadvanced I/System.out: longs = [[49, 50, 51, 52, 53, 54, 55, 56, 57, 58]]
    }

    public static void window(){
        Observable.interval(1,TimeUnit.SECONDS).take(120).window(12).subscribe(new Action1<Observable<Long>>() {
            @Override
            public void call(Observable<Long> longObservable) {
                System.out.println("longObservable = [" + longObservable + "]");
                longObservable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("aLong = [" + aLong + "]");
                    }
                });
            }
        });
//        11-28 12:00:06.897 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: longObservable = [rx.subjects.UnicastSubject@d1efa14]
//        11-28 12:00:06.899 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [0]
//        11-28 12:00:07.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [1]
//        11-28 12:00:08.894 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [2]
//        11-28 12:00:09.894 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [3]
//        11-28 12:00:10.894 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [4]
//        11-28 12:00:11.894 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [5]
//        11-28 12:00:12.894 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [6]
//        11-28 12:00:13.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [7]
//        11-28 12:00:14.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [8]
//        11-28 12:00:15.894 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [9]
//        11-28 12:00:16.894 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [10]
//        11-28 12:00:17.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [11]
//        11-28 12:00:18.894 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: longObservable = [rx.subjects.UnicastSubject@b67af1]
//        11-28 12:00:18.899 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [12]
//        11-28 12:00:19.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [13]
//        11-28 12:00:20.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [14]
//        11-28 12:00:21.894 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [15]
//        11-28 12:00:22.894 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [16]
//        11-28 12:00:23.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [17]
//        11-28 12:00:24.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [18]
//        11-28 12:00:25.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [19]
//        11-28 12:00:26.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [20]
//        11-28 12:00:27.894 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [21]
//        11-28 12:00:28.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [22]
//        11-28 12:00:29.893 30007-30156/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [23]
    }

    public static void groupBy(){
        ArrayList<SwordMan> swordMen = new ArrayList<>();
        swordMen.add(new SwordMan(1,"段正淳"));
        swordMen.add(new SwordMan(1,"鸠摩智"));
        swordMen.add(new SwordMan(0,"张三丰"));
        swordMen.add(new SwordMan(0,"张无忌"));
        swordMen.add(new SwordMan(2,"灵鹫宫"));
        swordMen.add(new SwordMan(0,"萧峰"));
        swordMen.add(new SwordMan(0,"扫地僧"));
        swordMen.add(new SwordMan(1,"慕容复"));
        swordMen.add(new SwordMan(2,"丁春秋"));
        Observable<GroupedObservable<Integer, SwordMan>> groupedObservableObservable = Observable.from(swordMen).groupBy(new Func1<SwordMan, Integer>() {
            @Override
            public Integer call(SwordMan swordMan) {
                return swordMan.getLevel();
            }
        });
        Observable.concat(groupedObservableObservable).subscribe(new Action1<SwordMan>() {
            @Override
            public void call(SwordMan swordMan) {
                System.out.println("swordMan = [" + swordMan + "]");
            }
        });
//        swordMan = [SwordMan{level=1, name='段正淳'}]
//        swordMan = [SwordMan{level=1, name='鸠摩智'}]
//        swordMan = [SwordMan{level=1, name='慕容复'}]
//        swordMan = [SwordMan{level=0, name='张三丰'}]
//        swordMan = [SwordMan{level=0, name='张无忌'}]
//        swordMan = [SwordMan{level=0, name='萧峰'}]
//        swordMan = [SwordMan{level=0, name='扫地僧'}]
//        swordMan = [SwordMan{level=2, name='灵鹫宫'}]
//        swordMan = [SwordMan{level=2, name='丁春秋'}]
    }

    public static void filter(){
        Observable.range(0,100).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 3 == 0;
            }
        }).buffer(10).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("integers = [" + integers + "]");
            }
        });
//        integers = [[0, 3, 6, 9, 12, 15, 18, 21, 24, 27]]
//        integers = [[30, 33, 36, 39, 42, 45, 48, 51, 54, 57]]
//        integers = [[60, 63, 66, 69, 72, 75, 78, 81, 84, 87]]
//        integers = [[90, 93, 96, 99]]
    }

    public static void elementAt(){
        Observable.range(0,20).elementAt(10).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
//        integer = [10] @warning 注意此处是从0开始计数
    }

    public static void distinct(){
        Observable.just(1,2,2,3,4,4,1).distinct().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
//        integer = 1
//        integer = 2
//        integer = 3
//        integer = 4

        Observable.range(0,5).repeat(3).distinct().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        integer = [3]
//        integer = [4]
    }

    public static void skip(){
        Observable.range(0,10).skip(5).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
//        integer = 5
//        integer = 6
//        integer = 7
//        integer = 8
//        integer = 9
    }

    public static void take(){
        Observable.range(0,10).take(5).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
//        integer = 0
//        integer = 1
//        integer = 2
//        integer = 3
//        integer = 4
    }

    public static void ignoreElements(){
        Observable.range(0,10).ignoreElements().subscribe(new Subscriber<Integer>() {
            @Override
            public void onStart() {
                super.onStart();
                System.out.println("RxJavaBasicalOperations.onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("RxJavaBasicalOperations.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("RxJavaBasicalOperations.onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
//        RxJavaBasicalOperations.onStart
//        RxJavaBasicalOperations.onCompleted
    }

    public static void throttleFirst(){
        Observable.interval(1,TimeUnit.SECONDS).take(120).throttleFirst(6,TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("aLong = [" + aLong + "]");
                    }
                });
//        11-28 16:31:16.301 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [0]
//        11-28 16:31:22.301 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [6]
//        11-28 16:31:29.301 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [13]
//        11-28 16:31:36.300 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [20]
//        11-28 16:31:42.301 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [26]
//        11-28 16:31:49.301 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [33]
//        11-28 16:31:55.301 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [39]
//        11-28 16:32:02.301 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [46]
//        11-28 16:32:08.301 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [52]
//        11-28 16:32:14.301 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [58]
//        11-28 16:32:21.300 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [65]
//        11-28 16:32:27.300 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [71]
//        11-28 16:32:33.300 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [77]
//        11-28 16:32:39.300 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [83]
//        11-28 16:32:45.300 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [89]
//        11-28 16:32:51.301 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [95]
//        11-28 16:32:58.300 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [102]
//        11-28 16:33:04.300 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [108]
//        11-28 16:33:10.301 21687-21870/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [114]
    }

    public static void throttleWithTimeOut(){
        Observable.interval(5,TimeUnit.SECONDS).take(24).throttleWithTimeout(4,TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onStart() {
                        System.out.println("RxJavaBasicalOperations.onStart");
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("RxJavaBasicalOperations.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("aLong = [" + aLong + "]");
                    }
                });
//        11-28 16:54:42.220 11751-11751/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onStart
//        11-28 16:54:51.227 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [0]
//        11-28 16:54:56.226 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [1]
//        11-28 16:55:01.226 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [2]
//        11-28 16:55:06.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [3]
//        11-28 16:55:11.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [4]
//        11-28 16:55:16.224 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [5]
//        11-28 16:55:21.226 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [6]
//        11-28 16:55:26.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [7]
//        11-28 16:55:31.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [8]
//        11-28 16:55:36.226 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [9]
//        11-28 16:55:41.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [10]
//        11-28 16:55:46.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [11]
//        11-28 16:55:51.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [12]
//        11-28 16:55:56.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [13]
//        11-28 16:56:01.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [14]
//        11-28 16:56:06.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [15]
//        11-28 16:56:11.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [16]
//        11-28 16:56:16.226 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [17]
//        11-28 16:56:21.226 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [18]
//        11-28 16:56:26.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [19]
//        11-28 16:56:31.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [20]
//        11-28 16:56:36.226 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [21]
//        11-28 16:56:41.225 11751-11960/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [22]
//        11-28 16:56:42.225 11751-11889/cn.zhengjun.androiddevelopmentadvanced I/System.out: aLong = [23]
//        11-28 16:56:42.225 11751-11889/cn.zhengjun.androiddevelopmentadvanced I/System.out: RxJavaBasicalOperations.onCompleted
    }

    public static void startWith(){
        Observable.just(5,10).startWith(Observable.range(0,5)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        integer = [3]
//        integer = [4]
//        integer = [5]
//        integer = [10]
    }

    public static void merge(){
        Observable.merge(Observable.range(0,5),Observable.range(5,5)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
//        integer = [0]
//        integer = [1]
//        integer = [2]
//        integer = [3]
//        integer = [4]
//        integer = [5]
//        integer = [10]
    }

    public static void concat(){
        Observable.concat(Observable.range(0,5),Observable.just(5,6,7,8)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
//        integer = 0
//        integer = 1
//        integer = 2
//        integer = 3
//        integer = 4
//        integer = 5
//        integer = 6
//        integer = 7
//        integer = 8
    }

    public static void zip(){
        Observable.zip(Observable.range(0, 5), Observable.just("hello", "android"), new Func2<Integer, String, String>() {
            @Override
            public String call(Integer integer, String s) {
                return integer+":"+s;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = [" + s + "]");
            }
        });
//        s = [0:hello]
//        s = [1:android]
    }

    public static void combineLast(){
        Observable.combineLatest(Observable.range(0, 5), Observable.just("hello", "rxjava"), new Func2<Integer, String, String>() {
            @Override
            public String call(Integer integer, String s) {
                return integer + ":"+s;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                System.out.println("RxJavaBasicalOperations.onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("RxJavaBasicalOperations.onCompleted");
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
//        RxJavaBasicalOperations.onStart
//        s = [4:hello]
//        s = [4:rxjava]
//        RxJavaBasicalOperations.onCompleted
    }

    public static void repeatWhen(){
//        Observable.range(0,6).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
//            @Override
//            public Observable<?> call(Observable<? extends Void> observable) {
//                return null;
//            }
//        })
    }
}
