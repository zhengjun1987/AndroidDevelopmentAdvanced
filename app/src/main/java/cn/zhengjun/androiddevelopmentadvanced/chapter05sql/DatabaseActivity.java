package cn.zhengjun.androiddevelopmentadvanced.chapter05sql;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zhengjun.androiddevelopmentadvanced.MessageEvent;
import cn.zhengjun.androiddevelopmentadvanced.MyApp;
import cn.zhengjun.androiddevelopmentadvanced.R;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.button_add)
    TextView buttonAdd;
    @BindView(R.id.button_update)
    TextView buttonUpdate;
    @BindView(R.id.button_delete)
    TextView buttonDelete;
    @BindView(R.id.button_query)
    TextView buttonQuery;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.button_recover)
    TextView buttonRecover;
    private MyApp application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);
        application = (MyApp) getApplication();
        buttonAdd.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonQuery.setOnClickListener(this);
        buttonRecover.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        User user = new User();
        user.setId(Long.parseLong(etId.getText().toString().trim()));
        user.setAge(Integer.parseInt(etAge.getText().toString().trim()));
        user.setName(etName.getText().toString().trim());
        DaoSession daoSession = application.getDaoSession();
        UserDao userDao = daoSession.getUserDao();
        switch (view.getId()) {
            case R.id.button_add:
                try {
                    long insert = userDao.insert(user);
                    System.out.println("insert = " + insert);
                    EventBus.getDefault().post(new MessageEvent(1,etName.getText().toString().trim(),null));
                } catch (SQLiteException e) {
                    Toast.makeText(this, "数据库异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_delete:
                userDao.delete(user);
                break;
            case R.id.button_update:
                userDao.update(user);
                break;
            case R.id.button_recover:
                System.out.println("DatabaseActivity.onClick");
                BackupHelper.loadBackupData(application);
                break;
            case R.id.button_query:
                List<User> users = userDao.loadAll();
                System.out.println("users = " + users);
                System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
                for (User user1 : users) {
                    tvQuery.setText(tvQuery.getText() + "\n" + user1.toString());
                }
                break;
        }
    }
}
