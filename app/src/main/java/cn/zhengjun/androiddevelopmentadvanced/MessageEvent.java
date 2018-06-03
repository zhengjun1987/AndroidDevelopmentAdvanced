package cn.zhengjun.androiddevelopmentadvanced;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2018/6/3
 * Summary : 在这里描述Class的主要功能
 */

public class MessageEvent {
    private final int mMsgCode;
    private final String mMsg;
    private final Object mWhat;

    public MessageEvent(int mMsgCode, String mMsg, Object mWhat) {
        this.mMsgCode = mMsgCode;
        this.mMsg = mMsg;
        this.mWhat = mWhat;
    }

    public int getmMsgCode() {
        return mMsgCode;
    }

    public String getmMsg() {
        return mMsg;
    }

    public Object getmWhat() {
        return mWhat;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "mMsgCode=" + mMsgCode +
                ", mMsg='" + mMsg + '\'' +
                ", mWhat=" + mWhat +
                '}';
    }
}
