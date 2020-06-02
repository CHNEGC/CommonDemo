package com.example.roomdemo;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvName = findViewById(R.id.tvName);
        final TextView tvAge = findViewById(R.id.tvAge);

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserInfoEntity userInfoEntity = new UserInfoEntity();
                userInfoEntity.setUserName("张三");
                userInfoEntity.setUserAge("2");
                userInfoEntity.setUserHeight("200cm");
                userInfoEntity.setUserKg("555Kg");
                userInfoEntity.setUserKg2("dddddf");
                userInfoEntity.setPhoneNum("18888888888");
                userInfoEntity.setPhoneNum2("18888888888");

                AppDataBase.getAppDataBase().getUserDao().insertUserInfo(userInfoEntity);
                final List<UserInfoEntity> userInfoEntity2 = AppDataBase.getAppDataBase().getUserDao().getAllData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (null == userInfoEntity2 || null == userInfoEntity2.get(0)) {
                            return;
                        }
                        tvAge.setText(userInfoEntity2.get(0).getUserAge());
                        tvName.setText(userInfoEntity2.get(0).getUserName()
                                + ","
                                + userInfoEntity2.get(0).getUserHeight()
                                + ","
                                + userInfoEntity2.get(0).getUserKg()
                                + ","
                                + userInfoEntity2.get(0).getUserKg2()
                                + ","
                                + userInfoEntity2.get(0).getPhoneNum()
                                + ","
                                + userInfoEntity2.get(0).getPhoneNum2());
                    }
                });
            }
        }).start();
    }
}
