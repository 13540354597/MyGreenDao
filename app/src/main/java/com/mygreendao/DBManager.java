package com.mygreendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mygreendao.data.DaoMaster;
import com.mygreendao.data.DaoSession;
import com.mygreendao.data.User;
import com.mygreendao.data.UserDao;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private final static String dbName = "test_db2";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }


    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }


    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insert(user);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insertInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.update(user);
    }

    /**
     * 查询用户列表
     */
    public List<User> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        List<User> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<User> queryUserList(int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        //升序
        qb.where(UserDao.Properties.Age.gt(age)).orderAsc(UserDao.Properties.Age);
        //降序
        qb.where(UserDao.Properties.Age.gt(age)).orderDesc(UserDao.Properties.Age);
        List<User> list = qb.list();
        return list;
    }


    /**
     * 条件查询eq
     */
    public User queryUser(String name) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        User user = qb.where(UserDao.Properties.Name.eq(name)).unique();
        return user;
    }

    /**
     * 条件查询like
     */
    public List<User> queryUserLike(String name) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Name.like(name + "%")).orderAsc(UserDao.Properties.Id);

        List<User> list = qb.list();
        return list;
    }

    /**
     * 条件查询between
     */
    public List<User> queryUserBetween(int min, int max) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.between(min, max)).orderAsc(UserDao.Properties.Id);

        List<User> list = qb.list();
        return list;
    }

    /**
     * 条件查询gt
     * gt等于>
     */
    public List<User> queryUserGt(int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.gt(age)).orderAsc(UserDao.Properties.Id);
        List<User> list = qb.list();
        return list;
    }

    /**
     * 条件查询lt
     * lt等于<
     */
    public List<User> queryUserLt(int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.lt(age)).orderAsc(UserDao.Properties.Id);
        List<User> list = qb.list();
        return list;
    }

    /**
     * 条件查询notEq
     * notEq不等于
     */
    public List<User> queryUserNotEq(int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.notEq(age)).orderAsc(UserDao.Properties.Id);
        List<User> list = qb.list();
        return list;
    }

    /**
     * 条件查询Ge
     * Ge等于>=
     */
    public List<User> queryUserNotGe(int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.ge(age)).orderAsc(UserDao.Properties.Id);
        List<User> list = qb.list();
        return list;
    }

    /**
     * 条件查询Le
     * Le等于<=
     */
    public List<User> queryUserNotLe(int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.le(age)).orderAsc(UserDao.Properties.Id);
        List<User> list = qb.list();
        return list;
    }

    /**
     * 原生的SQL查询
     */
    public List<User> queryUserSQL(int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(new WhereCondition.StringCondition("id" + "(SELECT age FROM FATHER WHERE AGE<45)"));
        List<User> list = qb.list();
        return list;
    }


    /**
     * 多线程查询
     */
    public List<User> queryUserThread(final int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        final Query qb = userDao.queryBuilder().build();
        final List<User> list;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//               list =qb.forCurrentThread().list();
//                return list;
//            }
//        }).start();


        return null;
    }

}