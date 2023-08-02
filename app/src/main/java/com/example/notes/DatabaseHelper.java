package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Notes.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Notes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDatabase) {
        myDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        myDatabase.execSQL("Create Table notes(id INT primary key AUTOINCREMENT, email TEXT, Title TEXT, NoteDetails TEXT, FOREIGN KEY (email) REFERENCES allusers(email))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDatabase, int i, int i1) {
        myDatabase.execSQL("drop Table if exists allusers");
    }
    public Boolean insertData(String email, String password){
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        long result = myDatabase.insert("allusers", null, contentValues);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean checkEmail(String Email){
        SQLiteDatabase myDatabase = this.getReadableDatabase();
        Cursor cursor = myDatabase.rawQuery("Select * from allusers where Email = ?", new String[]{Email});

        if(cursor.getCount() > 0){
            return  true;
        }
        else{
            return false;
        }
    }
    public Boolean checkEmailPassword (String Email, String Password){
        SQLiteDatabase myDatabase = this.getReadableDatabase();
        Cursor cursor = myDatabase.rawQuery("Select * from allusers where Email = ? and Password = ?", new String[]{Email, Password});

        if(cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
