package com.example.wsh666.studentsystem.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wsh666 on 2018/8/24.
 * 　 へ　　　　　  ／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　   /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */

public class CreateDB {

    private static  CreateDB createDB;
    private MyDBHelper myDBHelper;
    private static int DB_VERSION =3;

    public  CreateDB getInstance(){
        if(createDB==null){
            createDB=new CreateDB();
        }
        return  createDB;
    }

    public SQLiteDatabase createDB(Context context, String dbName, SQLiteDatabase.CursorFactory factory){
        //获得SQLiteOpenHelper实例
        myDBHelper=new MyDBHelper(context,dbName,factory,DB_VERSION);
        //创建数据库同时获取SQLiteDatabase对象
        SQLiteDatabase db=myDBHelper.getWritableDatabase();
        return  db;
    }
}
