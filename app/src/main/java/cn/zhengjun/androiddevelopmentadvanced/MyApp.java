package cn.zhengjun.androiddevelopmentadvanced;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

import cn.zhengjun.androiddevelopmentadvanced.chapter05sql.DaoMaster;
import cn.zhengjun.androiddevelopmentadvanced.chapter05sql.DaoSession;

public class MyApp extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        loadBackupFiles();
        initGreendao();
    }

    private void loadBackupFiles() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        System.out.println("externalStorageDirectory = " + externalStorageDirectory.getAbsolutePath());
        String[] list = externalStorageDirectory.list();
        if (list != null) {
            for (String s : list) {
                System.out.println("外存二级文件路径 = " + s);
            }
        }
        File dataDirectory = Environment.getDataDirectory();
        System.out.println("dataDirectory.getAbsolutePath() = " + dataDirectory.getAbsolutePath());
        String[] list1 = dataDirectory.list();
        if (list1 != null) {
            for (String s : list1) {
                System.out.println("数据存储二级文件路径 = " + s);
            }
        }

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
