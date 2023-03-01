package com.example.journalsapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.journalsapp.JournalApplication
import com.example.journalsapp.data.DefaultPager
import com.example.journalsapp.data.JournalRepository
import com.example.journalsapp.model.Journal
import kotlinx.coroutines.launch

private const val TAG = "JournalViewModel"


data class ScreenState(
    val isLoading: Boolean = false,
    val journals: List<Journal> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0,
    var limit: Int = 10,
)

class JournalViewModel(private val journalRepository: JournalRepository): ViewModel() {

    var state by mutableStateOf(ScreenState())

    private val pager = DefaultPager(
        initialKey = state.page,
        limit = state.limit,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage,nextLimit ->
            journalRepository.getJournals(nextPage,nextLimit)

        },
        getNextKey = {
            state.page + state.limit
        },
        getNextLimit = {
            state.limit + 10
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },

        onSuccess = { journals, newKey ->
            state = state.copy(
                journals = state.journals + journals,
                page = newKey,
                endReached = journals.isEmpty()
            )
        }
    )

    init {
        loadNextJournals()
    }

    fun loadNextJournals(){
        viewModelScope.launch {
            pager.loadNextItems()
        }
    }


    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JournalApplication)
                val journalRepository = application.container.journalRepository
                JournalViewModel(journalRepository)
            }
        }
    }
}
