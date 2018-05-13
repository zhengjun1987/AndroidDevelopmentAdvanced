package cn.zhengjun.androiddevelopmentadvanced.chapter08reativex;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2018/5/10
 * Summary : 在这里描述Class的主要功能
 */

public class Translation {

    private String status;
    private content content;

    private static class content{
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;

        @Override
        public String toString() {
            return "content{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", vendor='" + vendor + '\'' +
                    ", out='" + out + '\'' +
                    ", errNo=" + errNo +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Translation{" +
                "status='" + status + '\'' +
                ", content=" + content +
                '}';
    }

    public void show() {
        System.out.println(toString());
    }
}