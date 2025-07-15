package com.iam.noteapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iam.noteapp.module.Note
import com.iam.noteapp.repo.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
            loadNotes()
        }
    }


    fun loadNotes() {
        viewModelScope.launch {
            val notesList = repository.getAllNotes()
            _notes.postValue(notesList)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
            loadNotes() // Refresh list
        }
    }
}
