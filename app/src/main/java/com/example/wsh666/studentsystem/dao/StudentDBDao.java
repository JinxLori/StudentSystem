package com.example.wsh666.studentsystem.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.wsh666.studentsystem.bean.StudentBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wsh666 on 2018/8/25.
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

public class StudentDBDao {

    /*添加学生*/
    public void insertStudents(StudentBean studentBean, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("stuName", studentBean.getStuName());
        values.put("class", studentBean.getStuClass());
        values.put("mobile", studentBean.getMobile());
        values.put("sex", studentBean.getSex());
        values.put("favorite", studentBean.getFavorite());
        db.insert("tb_student", null, values);
        values.clear();
    }

    /*查询出所有班级*/
    public List<String> getClassFromTableStudent(SQLiteDatabase db) {
        List<String> classes = new ArrayList<>();
        Cursor cursor = db.query("tb_student", new String[]{"class"}, null, null, "class", null, null);
        if (cursor.moveToFirst()) {
            do {
                //遍历cusor对象，取出数据
                String stuClass = cursor.getString(cursor.getColumnIndex("class"));
                classes.add(stuClass);
            } while (cursor.moveToNext());
        }
        /*Log.e("MainActivity.class", classes.toString());*/
        return classes;
    }

    /*查询出班级对应的所有学生*/
    public Map<String, List<StudentBean>> getStudentForEveryClass(SQLiteDatabase db, List<String> classes) {
        Map<String, List<StudentBean>> map = new HashMap<>();
        for (int i = 0; i < classes.size(); i++) {
            String group_class = classes.get(i);
            /*Log.e("MainActivity.this", group_class);*/
            List<StudentBean> stus = new ArrayList<>();
            Cursor cursor = db.query("tb_student", null, "class=?", new String[]{group_class}, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    //遍历cusor对象，取出数据
                    int stuId = cursor.getInt(cursor.getColumnIndex("stuId"));
                    String stuName = cursor.getString(cursor.getColumnIndex("stuName"));
                    String stuClass = cursor.getString(cursor.getColumnIndex("class"));
                    String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
                    String sex = cursor.getString(cursor.getColumnIndex("sex"));
                    String favorite = cursor.getString(cursor.getColumnIndex("favorite"));

                    StudentBean student = new StudentBean();
                    student.setStuId(stuId);
                    student.setStuName(stuName);
                    student.setStuClass(stuClass);
                    student.setSex(sex);
                    student.setMobile(mobile);
                    student.setFavorite(favorite);
                    Log.e("MainActivity.this", student.toString());
                    stus.add(student);
                } while (cursor.moveToNext());
            }
            map.put(group_class, stus);
        }
        return map;
    }

    /*查询所有学生*/
    public List<StudentBean> getStudentFromDB(SQLiteDatabase db) {
        List<StudentBean> stuList = new ArrayList<>();
        Cursor cursor = db.query("tb_student", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                //遍历cusor对象，取出数据
                int stuId = cursor.getInt(cursor.getColumnIndex("stuId"));
                String stuName = cursor.getString(cursor.getColumnIndex("stuName"));
                String stuClass = cursor.getString(cursor.getColumnIndex("class"));
                String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                String favorite = cursor.getString(cursor.getColumnIndex("favorite"));

                StudentBean student = new StudentBean();
                student.setStuId(stuId);
                student.setStuName(stuName);
                student.setStuClass(stuClass);
                student.setSex(sex);
                student.setMobile(mobile);
                student.setFavorite(favorite);

                stuList.add(student);

            } while (cursor.moveToNext());
        }
        return stuList;
    }

    /*根据stuId获取学生*/
    public StudentBean getStudentById(SQLiteDatabase db,String stuId){
        StudentBean student = new StudentBean();
        Cursor cursor=db.query("tb_student",null,"stuId=?",new String[]{stuId},null,null,null);
        if (cursor.moveToFirst()) {
            do {
                //遍历cusor对象，取出数据
                String stuName = cursor.getString(cursor.getColumnIndex("stuName"));
                String stuClass = cursor.getString(cursor.getColumnIndex("class"));
                String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                String favorite = cursor.getString(cursor.getColumnIndex("favorite"));

                student.setStuId(Integer.parseInt(stuId));
                student.setStuName(stuName);
                student.setStuClass(stuClass);
                student.setSex(sex);
                student.setMobile(mobile);
                student.setFavorite(favorite);

            } while (cursor.moveToNext());
        }
        return student;
    }

    /*更新学生*/
    public void updateStudent(SQLiteDatabase db,StudentBean studentBean,String stuId){
        ContentValues values = new ContentValues();
        values.put("stuName", studentBean.getStuName());
        values.put("class", studentBean.getStuClass());
        values.put("mobile", studentBean.getMobile());
        values.put("sex", studentBean.getSex());
        values.put("favorite", studentBean.getFavorite());
        db.update("tb_student",values,"stuId=?",new String[]{stuId});
        values.clear();
    }

    /*删除学生*/
    public void deleteStudent(SQLiteDatabase db,String stuId){
        db.delete("tb_student","stuId=?",new String[]{stuId});
    }
}
