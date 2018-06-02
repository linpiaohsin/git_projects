package com.example.linpiaohsin.weicotest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by linpiaohsin on 2017/11/14.
 */

public class DailogOpenHelper extends SQLiteOpenHelper {
    public DailogOpenHelper(Context context) {
        super(context, "dailog1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table dialog1(" +
                "_id integer primary key autoincrement," +
                "title varchar(20)," +
                "mod int," +
                "content varchar(600)," +
                "time varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
