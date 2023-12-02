package com.example.mysqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Myhelper extends SQLiteOpenHelper {
    private static final String dbname = "Users";
    private static final int version = 1;


    public Myhelper(Context context) {

        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE student(id integer primary key autoincrement , Name text , Branch text, Subject text)";
        db.execSQL(query);


    }
    public boolean insert_data(String name, String branch, String subject){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Branch", branch);
        values.put("Subject", subject);

        long r = db.insert("student", null, values);
        if (r==-1){
            return false;
        }else {
            return true;
        }

    }
    public Cursor read_data(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from student" , null);
        return cursor;
    }

    public boolean update_data(String name, String branch, String subject){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Name", name);
        Cursor cursor = db.rawQuery("select * from student where Branch=?", new String[]{branch});
        if (cursor.getCount()>0){
            long r =db.update("student" , values, "Branch=?",new String[]{branch});
            if (r==-1){
                return false;
            }else {
                return true;
            }
        }


        return false;
    }

    public boolean delete_data(String name){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from student where Name=?",new String[]{name});
        if (cursor.getCount()>0){
            long r =db.delete("student", "Name=?",new String[]{name});
            if (r==-1){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists student");
        onCreate(db);


    }
}
