package com.iam.noteapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iam.noteapp.module.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
