package com.app.renhao.dietwallet;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2016-04-29.
 */
public class DBManager {

    private static DBManager mDBManger;
    private Context mContext;
    private RealmConfiguration config;

    public DBManager(Context context) {
        mContext = context;
        config = new RealmConfiguration.Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
    public static DBManager getInstance(Context context) {

        if (mDBManger == null) {
            mDBManger = new DBManager(context);
        }
        return mDBManger;
    }
    public RealmResults<DataPackage> getDBresult(){
        Realm mRealm = Realm.getDefaultInstance();
        return mRealm.where(DataPackage.class).findAll();
    }

    public void addDailyDietDate(Context context, int calorie,int money,String date, String food){
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        DataPackage dp = mRealm.createObject(DataPackage.class);
        DBManager db= DBManager.getInstance(context);
        dp.setSno(db.getDBresult().size());
        dp.setCalorie(calorie);
        dp.setMoney(money);
        dp.setDate(date);
        dp.setFoodname(food);
        mRealm.commitTransaction();
        mRealm.close();
    }
    public  RealmResults<DataPackage> getLastDate(){
        Realm mRealm = Realm.getDefaultInstance();
        return mRealm.where(DataPackage.class).equalTo("sno",getDBresult().size()-1).findAll();
    }
}
