package cn.zhengjun.androiddevelopmentadvanced.chapter08reativex;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2018/5/10
 * Summary : 在这里描述Class的主要功能
 */

public interface GetRequestInterface {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=你好")
    Observable<Translation> getCall();
}
