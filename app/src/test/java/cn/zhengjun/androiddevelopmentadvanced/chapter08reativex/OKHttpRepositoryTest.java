package cn.zhengjun.androiddevelopmentadvanced.chapter08reativex;

import org.junit.Test;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/11/29 17:30
 * Summary : null
 */
public class OKHttpRepositoryTest {
    @Test
    public void requestAsync() throws Exception {
        OKHttpRepository okHttpRepository = new OKHttpRepository();
        okHttpRepository.requestAsync("115.204.105.219")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("OKHttpRepositoryTest.onCompleted");
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
    }

}