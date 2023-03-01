package com.example.journalsapp.data

import com.example.journalsapp.model.Journal
import com.example.journalsapp.network.JournalApiService
import retrofit2.Response

interface JournalRepository {
    suspend fun getJournals(offset: Int, limit: Int): Response<List<Journal>>
}

class NetworkJournalRepository(
    private val journalApiService: JournalApiService
): JournalRepository {

    override suspend fun getJournals(offset: Int, limit: Int): Response<List<Journal>> =
        journalApiService.getJournals(offset,limit)

}