package cn.zhengjun.androiddevelopmentadvanced.chapter08reativex;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/11/28 12:08
 * Summary : null
 */

public class SwordMan {
    private String name;
    private int level;

    public SwordMan(int level, String name) {
        this.level = level;
        this.name = name;
    }

    @Override
    public String toString() {
        return "SwordMan{" +
                "level=" + level +
                ", name='" + name + '\'' +
                '}';
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
