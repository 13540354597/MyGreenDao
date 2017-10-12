package com.mygreendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mygreendao.data.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }


    private void test() {
        DBManager dbManager = DBManager.getInstance(this);
//        for (int i = 0; i < 5; i++) {
//            User user = new User();
//            user.setId((long) i);
//            user.setAge(i * 3);
//            if (i== 3) {
//                user.setName("张杰");
//            } else {
//                user.setName("第" + i + "人");
//            }
//
//            dbManager.insertUser(user);
//        }


        List<User> userList = dbManager.queryUserBetween(3, 6);
        for (User user : userList) {
            Log.e("TAG", "queryUserList--after--->" + user.getId() + "---" + user.getName() + "--" + user.getAge());
        }

//        User user = dbManager.queryUser("第1人");
//        Log.e("TAG", "queryUserList--after--->" + user.getId() + "---" + user.getName() + "--" + user.getAge());
    }
}
