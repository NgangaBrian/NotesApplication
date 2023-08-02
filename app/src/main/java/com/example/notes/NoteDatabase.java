package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.CaseMap;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {

    public static final String databaseName = "Notes.db";
    public static String DB_NAME = "notes";

    public NoteDatabase(@Nullable Context context) {
        super(context, "Notes.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase myDatabase) {
        String query = "Create table notes(id INT PRIMARY KEY AUTOINCREMENT, " +
                "Title TEXT, " +
                "Description TEXT, " +
                "DateCreated TEXT, " +
                "TimeCreated)";
        myDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDatabase, int i, int i1) {
        myDatabase.execSQL("Drop Table if exists notes");
        }
    public long AddNote (Note noteModel){
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
    public List<Note> getNote(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> allNote = new ArrayList<>();

        String queryStatement = "Select * From notes";
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

