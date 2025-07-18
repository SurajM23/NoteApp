package com.iam.noteapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iam.noteapp.module.Note
import com.iam.noteapp.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    init {
        loadNotes()
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
            loadNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
            loadNotes()
        }
    }

    fun loadNotes() {
        viewModelScope.launch {
            _notes.value = repository.getAllNotes()
        }
    }
}
