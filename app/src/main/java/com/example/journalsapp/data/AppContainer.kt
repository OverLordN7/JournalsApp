package com.example.journalsapp.data

import com.example.journalsapp.network.JournalApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val journalRepository: JournalRepository
}

class DefaultAppContainer: AppContainer{

    var BASE_URL = "http://pressa-api.imb2bs.com/api/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: JournalApiService by lazy {
        retrofit.create(JournalApiService::class.java)
    }

    override val journalRepository: JournalRepository by lazy {
        NetworkJournalRepository(retrofitService)
    }
}