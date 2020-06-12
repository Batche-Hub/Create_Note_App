package com.example.sqliteapplication;

import java.io.Serializable;

public class Note implements Serializable {

    private int noteId;
    private String noteTitle;
    private String noteContent;
    private String categorie;

    public Note()  {

    }

    public Note(String noteTitle, String noteContent, String categorie) {
        this.noteTitle= noteTitle;
        this.noteContent= noteContent;
        this.categorie = categorie;
    }

    public Note(int parseInt, String noteTitle, String noteContent, String categorie) {
        this.noteId = parseInt;
        this.noteTitle= noteTitle;
        this.noteContent= noteContent;
        this.categorie = categorie;
    }


    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }


    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return this.noteTitle + " / "+categorie;
    }
}