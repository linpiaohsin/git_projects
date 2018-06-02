package com.example.linpiaohsin.weicotest.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linpiaohsin.weicotest.entity.Dialog;

/**
 * Created by linpiaohsin on 2017/11/14.
 */

public class DailogDao {
    SQLiteDatabase sqLiteDatabase;
    DailogOpenHelper dailogOpenHelper;
    public DailogDao(Context context){
        dailogOpenHelper=new DailogOpenHelper(context);
        sqLiteDatabase=dailogOpenHelper.getReadableDatabase();

    }
    public void insert(Dialog dialog){
        String sql="insert into dialog1(title,mod,content,time) values(?,?,?,?)";
        sqLiteDatabase.execSQL(sql,new Object[]{dialog.getTitle(),dialog.getMod(),dialog.getContent()
                ,dialog.getTime()});

    }
    public Cursor search(){

        Cursor cursor=sqLiteDatabase.rawQuery("select * from dialog1",null);
        return cursor;
    }


}
