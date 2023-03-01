package com.example.journalsapp.network

import com.example.journalsapp.model.Journal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface JournalApiService {

    @Headers("Project-id: 2","Language: ru")
    @GET("v1/journal/categories/0/")
    suspend fun getJournals(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<List<Journal>>

}