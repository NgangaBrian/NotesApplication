package com.example.notes;

public class Note  {
    int id;
    String title, description, noteDate, noteTime;

    Note(){

    }

    public Note(String title, String description, String noteDate, String noteTime) {

        this.title = title;
        this.description = description;
        this.noteDate = noteDate;
        this.noteTime = noteTime;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(String noteTime) {
        this.noteTime = noteTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
