package com.iam.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iam.noteapp.module.Note
import com.iam.noteapp.ui.theme.NoteAppTheme
import com.iam.noteapp.viewModel.NoteViewModel
import com.iam.noteapp.views.NoteGridView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesApp()
                }
            }
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable()
    fun GreetingPreview() {
        NotesApp()
    }

    @Composable
    fun NotesApp() {
        var noteText by remember { mutableStateOf("") }
        var notes by remember { mutableStateOf(mutableListOf<Note>()) }

        viewModel.notes.observe(this) {
            notes = it.toMutableList()
        }

        viewModel.loadNotes()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteGridView(
                notes = notes,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    placeholder = { Text("Enter note...") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    maxLines = 4,
                    singleLine = false
                )

                IconButton(
                    onClick = {
                        if (noteText.isNotBlank()) {
                            val note = Note(
                                title = "Note ${notes.size + 1}",
                                des = noteText
                            )
                            notes.add(note)
                            noteText = ""
                            viewModel.addNote(note)
                        }
                    },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send"
                    )
                }
            }
        }
    }
}