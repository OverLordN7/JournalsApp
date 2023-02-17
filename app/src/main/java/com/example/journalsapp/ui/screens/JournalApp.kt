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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.journalsapp.R

@Preview (showBackground = true)
@Composable
fun JournalApp(
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
    ){
        items(10){
            JournalCard()
        }
    }
}

@Composable
fun JournalCard(
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.padding(4.dp),
        elevation = 4.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.loading),
            contentDescription = "loadingImage",
            modifier = Modifier.size(200.dp,280.dp),
            contentScale = ContentScale.Crop,
        )
    }
}