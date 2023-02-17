package com.example.journalsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.journalsapp.R
import com.example.journalsapp.data.Journal


@Composable
fun JournalApp(
    journalList: MutableState<List<Journal>>,
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
    ){
        items(journalList.value.size){
            JournalCard(journalList.value[it])
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
            model = journal.cover,
            contentDescription = journal.name,
            modifier = Modifier.size(200.dp,280.dp),
        )
    }
}