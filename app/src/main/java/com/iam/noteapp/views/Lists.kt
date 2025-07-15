package com.iam.noteapp.views

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.iam.noteapp.module.Note

@Composable
fun NoteGridView(
    notes: List<Note>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notes) { note ->
            NoteCard(note = note)
        }
    }
}

@Composable
fun NoteCard(note: Note) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(onClick = {
                Toast.makeText(context, "Title ${note.title}", Toast.LENGTH_SHORT).show()
            }),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 4.dp,
                shadowElevation = 4.dp
            ) {
                Text(
                    text = note.title,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Text(
                text = note.des,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}