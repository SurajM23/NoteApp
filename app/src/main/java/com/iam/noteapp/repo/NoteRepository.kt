package com.iam.noteapp.repo

import com.iam.noteapp.module.Note

import com.iam.noteapp.db.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    suspend fun insert(note: Note) = noteDao.insert(note)

    suspend fun getAllNotes(): List<Note> = noteDao.getAllNotes()

    suspend fun delete(note: Note) = noteDao.delete(note)
}