package com.example.studentlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Db_Operations extends SQLiteOpenHelper {
    public Db_Operations(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "studentList.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STUDENTS( ID INTEGER PRIMARY KEY AUTOINCREMENT, IDNUM TEXT UNIQUE, FIRSTNAME TEXT, LASTNAME TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS STUDENTS;");
        onCreate(db);
    }

    public void insert_student(String idNum, String fname, String Lname)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDNUM", idNum);
        contentValues.put("FIRSTNAME", fname);
        contentValues.put("LASTNAME", Lname);
        this.getWritableDatabase().insertOrThrow("STUDENTS","",contentValues);
    }

    public void delete_student(String idNum){
        this.getWritableDatabase().delete("STUDENTS","IDNUM = '"+idNum+"'", null);
    }

    public void update_students(String IdNum, String fname, String lName){
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME = '"+fname+"', LASTNAME = '"+lName+"' WHERE IDNUM = '"+IdNum+"'");
    }

   public boolean SearchID(String search){
        String sql = "SELECT IDNUM FROM STUDENTS WHERE IDNUM = '"+search+"'";
        Cursor cursor = this.getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0)
            return true;

        return false;
    }

    public Cursor viewAllStudent(){
        return this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS", null);
    }


}
