package cn.zhengjun.androiddevelopmentadvanced.chapter08reativex;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/11/29 17:10
 * Summary : null
 */

public class OKHttpRepository {

    public static final String URL = "http://ip.taobao.com/service/getIpInfo.php";

    public Observable<String> requestAsync(final String ip){
        System.out.println("OKHttpRepository.requestAsync  ip = [" + ip + "]");
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder()
                        .add("ip", ip)
                        .build();
                System.out.println("formBody = " + formBody.name(0) + "  "+formBody.value(0));
                final Request request = new Request.Builder()
                        .url(URL)
                        .post(formBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        System.out.println("call = " + call.request().toString());
                        System.out.println("response = " + response);
                        subscriber.onNext(response.body().string());
                        subscriber.onCompleted();
                    }
                });

            }
        });
    }
}
