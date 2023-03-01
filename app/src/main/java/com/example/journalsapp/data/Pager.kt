package com.example.journalsapp.data

import retrofit2.Response

interface Pager<Int,Item> {
    suspend fun loadNextItems()
    fun reset()
}

class DefaultPager<Int,Item>(
    private val initialKey: Int,
    private val limit: Int,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Int,nextLimit: Int) -> Response<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Int,
    private inline val getNextLimit: suspend(List<Item>) -> Int,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (journals: List<Item>, newKey: Int ) -> Unit
): Pager<Int,Item>{

    private var currentKey = initialKey
    private var currentLimit = limit

    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest){
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey,currentLimit)
        isMakingRequest = false

        if (result.isSuccessful){
            val journals = result.body()
            currentKey = getNextKey(journals!!)
            currentLimit = getNextLimit(journals)
            onSuccess(journals,currentKey)
            onLoadUpdated(false)

        } else{
            val error = Throwable()
            onError(error)
            onLoadUpdated(false)
            return
        }


    }

    override fun reset() {
        currentKey = initialKey
    }

}