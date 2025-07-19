package com.iam.noteapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iam.noteapp.module.Note
import com.iam.noteapp.viewModel.NoteViewModel
import com.iam.noteapp.views.NoteGridView

@Composable
fun NoteScreen(viewModel: NoteViewModel) {

    var noteText by remember { mutableStateOf("") }

    val notes by viewModel.notes.collectAsState()

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
                    .padding(end = 8.dp)
            )

            IconButton(
                onClick = {
                    if (noteText.isNotBlank()) {
                        val note = Note(
                            title = "Note ${notes.size + 1}",
                            des = noteText
                        )
                        viewModel.addNote(note)
                        noteText = ""
                    }
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(Icons.Default.Send, contentDescription = "Send")
            }
        }
    }
}
