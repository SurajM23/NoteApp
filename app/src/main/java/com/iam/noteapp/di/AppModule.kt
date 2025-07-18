package com.iam.noteapp.di

import android.app.Application
import androidx.room.Room
import com.iam.noteapp.db.NoteDao
import com.iam.noteapp.db.NoteDatabase
import com.iam.noteapp.repo.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    @Provides
    fun provideNoteDao(db: NoteDatabase): NoteDao = db.noteDao()

    @Provides
    @Singleton
    fun provideRepository(noteDao: NoteDao): NoteRepository = NoteRepository(noteDao)
}
