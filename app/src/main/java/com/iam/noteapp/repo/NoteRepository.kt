package com.iam.noteapp.repo

import android.content.Context
import com.iam.noteapp.db.NoteDatabase
import com.iam.noteapp.module.Note

class NoteRepository(context: Context) {
    private val db = NoteDatabase.getInstance(context)
    private val dao = db.noteDao()

    suspend fun insert(note: Note) {
        dao.insert(note)
    }

    suspend fun getAllNotes(): List<Note> {
        return dao.getAllNotes()
    }

    suspend fun delete(note: Note) {
        dao.delete(note)
    }
}
