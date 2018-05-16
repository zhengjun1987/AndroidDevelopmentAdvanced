package cn.zhengjun.androiddevelopmentadvanced;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import cn.zhengjun.androiddevelopmentadvanced.chapter05sql.DaoMaster;
import cn.zhengjun.androiddevelopmentadvanced.chapter05sql.DaoSession;

public class MyApp extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreendao();
    }

    private void initGreendao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "user.db");
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        daoSession = new DaoMaster(writableDatabase).newSession();
        System.out.println("Greendao初始化 = " + daoSession);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
