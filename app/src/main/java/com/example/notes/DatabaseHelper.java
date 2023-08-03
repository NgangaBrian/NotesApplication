package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    DatabaseHelper databaseHelper;

    public static final String databaseName = "Notes.db";

    public DatabaseHelper(@Nullable Context context) {

        super(context, "Notes.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase myDatabase) {
        myDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        String query = "Create Table note(id INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT, Description TEXT, DateCreated TEXT, TimeCreated TEXT)";
        myDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDatabase, int i, int i1) {
        myDatabase.execSQL("drop Table if exists allusers");
        myDatabase.execSQL("Drop Table if exists note");
        onCreate(myDatabase);
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
    public long AddNotes (Note noteModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", noteModel.getTitle());
        contentValues.put("Description", noteModel.getDescription());
        contentValues.put("DateCreated", noteModel.getNoteDate());
        contentValues.put("TimeCreated", noteModel.getNoteTime());

        long ID = db.insert("notes", null, contentValues);
        Log.d("Inserted", "id -->" + ID);
        return ID;
    }
    public void closeDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
    public Boolean AddNote (String title, String description, String datecreated, String timecreated){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", title);
        contentValues.put("Description", description);
        contentValues.put("DateCreated", datecreated);
        contentValues.put("TimeCreated", timecreated);
        long result = db.insert("note", null, contentValues);

        if (result != 1) return false;
        else {
            return  true;
        }
    }
    public boolean deleteNoteById(int noteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete("note", "id = ?", new String[]{String.valueOf(noteId)});
        return deletedRows > 0;
    }
    public List<Note> getNote(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> allNote = new ArrayList<>();

        String queryStatement = "Select * From note";
        Cursor cursor = db.rawQuery(queryStatement, null);

        if(cursor.moveToFirst()){
            do {
                Note noteModel = new Note();
                noteModel.setId(cursor.getInt(0));
                noteModel.setTitle(cursor.getString(1));
                noteModel.setDescription(cursor.getString(2));
                noteModel.setNoteDate(cursor.getString(3));
                noteModel.setNoteTime(cursor.getString(4));

                allNote.add(noteModel);

            } while (cursor.moveToNext());
        }
        return allNote;
    }
}
