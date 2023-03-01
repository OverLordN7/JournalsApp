package com.example.journalsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.journalsapp.model.Journal


@Composable
fun HomeScreen(
    journalViewModel: JournalViewModel,
    modifier: Modifier = Modifier
){
    val state = journalViewModel.state

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
    ){
        items(journalViewModel.state.journals.size){
            val journal = state.journals[it]
            if (it >= state.journals.size -1 && !state.endReached && !state.isLoading){
                journalViewModel.loadNextJournals()
            }
            JournalCard(journal)
        }
        item {
            if (state.isLoading){
                Row (
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    CircularProgressIndicator()
                }
            }
        }
        item {
            if (state.isLoading){
                Row (
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun JournalCard(
    journal: Journal,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.padding(4.dp),
        elevation = 4.dp,
    ) {
        AsyncImage(
            model = journal.last_issue.cover,
            contentDescription = journal.name,
            modifier = Modifier.size(200.dp,270.dp),
        )
    }
}