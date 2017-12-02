package cn.zhengjun.androiddevelopmentadvanced.chapter08reativex;

import org.junit.Test;

import java.util.Arrays;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/12/1 9:56
 * Summary : null
 */
public class MergeTestTest {
    @Test
    public void test1() throws Exception {
        String[] split = "2017.12.1".split("\\.");
        System.out.println(Arrays.toString(split));
    }

}