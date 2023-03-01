package com.example.journalsapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.journalsapp.R
import com.example.journalsapp.ui.screens.HomeScreen
import com.example.journalsapp.ui.screens.JournalViewModel

@Composable
fun JournalApp(modifier: Modifier = Modifier){
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(id = R.string.app_name)) }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val journalViewModel: JournalViewModel = viewModel(factory = JournalViewModel.Factory)

            HomeScreen(journalViewModel = journalViewModel)
        }
    }
}
