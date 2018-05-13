package cn.zhengjun.androiddevelopmentadvanced.chapter08reativex;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2018/5/10
 * Summary : 在这里描述Class的主要功能
 */

public class GsonTest {
    public static void main(String[] args) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("userId","uid198706088852");
        stringStringHashMap.put("username","张无忌");
        Gson gson = new Gson();
        String json = gson.toJson(stringStringHashMap);
        System.out.println("json = " + json);
//        json = {"userId":"uid198706088852","username":"张无忌"}

        String 张无忌 = gson.toJson(new SwordMan(1, "张无忌"));
        System.out.println("张无忌 = " + 张无忌);
//        张无忌 = {"name":"张无忌","level":1}
    }
}
