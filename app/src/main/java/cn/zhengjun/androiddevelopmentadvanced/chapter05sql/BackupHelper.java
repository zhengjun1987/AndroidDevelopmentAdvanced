package cn.zhengjun.androiddevelopmentadvanced.chapter05sql;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

import java.util.List;

import cn.zhengjun.androiddevelopmentadvanced.MyApp;

public class BackupHelper {
    public static void loadBackupData(MyApp context) {
        System.out.println("BackupHelper.loadBackupData  " + "context = [" + context + "]");
        UserDao currentUserDao = context.getDaoSession().getUserDao();
        List<User> users = currentUserDao.loadAll();
        long maxId = getMaxId(users);
        System.out.println("本地数据库中最大的id = " + maxId);

        String concat = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/user.db");
        System.out.println("备份数据路径 = " + concat);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, concat);
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        UserDao userDao = new DaoMaster(writableDatabase).newSession().getUserDao();
        List<User> userList = userDao.loadAll();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            user.setId(maxId+1+i);
            long insert = currentUserDao.insert(user);
            System.out.println("成功恢复 = " + user);
        }

        Toast.makeText(context, "数据恢复完成！", Toast.LENGTH_SHORT).show();
    }
//        05-16 15:50:41.260 14442-14442/cn.zhengjun.androiddevelopmentadvanced I/System.out: BackupHelper.loadBackupData  context = [cn.zhengjun.androiddevelopmentadvanced.MyApp@107cbf12]
//        05-16 15:50:41.263 14442-14442/cn.zhengjun.androiddevelopmentadvanced I/System.out: 本地数据库中最大的id = 1
//        备份数据路径 = /mnt/internal_sd/user.db
//        05-16 15:50:41.307 14442-14442/cn.zhengjun.androiddevelopmentadvanced I/System.out: 成功恢复 = User{id=2, name='Zhengzhou', age=55}
//        05-16 15:50:41.318 14442-14442/cn.zhengjun.androiddevelopmentadvanced I/System.out: 成功恢复 = User{id=3, name='Banking', age=57}
//        05-16 15:50:41.329 14442-14442/cn.zhengjun.androiddevelopmentadvanced I/System.out: 成功恢复 = User{id=4, name='Nanking', age=57}

    private static long getMaxId(List<User> users) {
        long i = 0;
        if (users != null) {
            for (User user : users) {
                if (user.getId() > i)
                    i = user.getId();
            }
        }
        return i;
    }
}
