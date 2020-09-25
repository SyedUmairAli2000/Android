package com.example.myapplicationsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLite extends SQLiteOpenHelper {
    public MySQLite(Context context)
    {
        super(context, "StudentDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table Student(Id Integer Primary Key Autoincrement,Name text,Phone text,Email text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public boolean insertStudent(String Name,String Phone,String Email)
    {

        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Name",Name);
        values.put("Phone",Phone);
        values.put("Email",Email);

       long res= db.insert("Student",null,values);
       return res!=-1;

    }
    public boolean deleteStudent(String id)
    {

        SQLiteDatabase db=getWritableDatabase();

        long res= db.delete("Student","Id=?",new String[]{id});
        return res>0;

    }
    public boolean updateStudent(String id,String Name,String Phone,String Email)
    {

        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Id",id);
        values.put("Name",Name);
        values.put("Phone",Phone);
        values.put("Email",Email);

        long res= db.update("Student",values,"Id=?",new String[]{id});
        return res>0;

    }


    public Cursor fetchStudents()
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from Student",null);
        return cursor;
    }
    public Cursor fetchStudent(String id)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from Student where Id="+id,null);
        return cursor;
    }


}
