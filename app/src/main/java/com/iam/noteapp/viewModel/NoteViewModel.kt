package com.iam.noteapp.viewModel

import androidx.lifecycle.*
import com.iam.noteapp.module.Note
import com.iam.noteapp.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

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
            _notes.postValue(repository.getAllNotes())
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
            loadNotes()
        }
    }
}
