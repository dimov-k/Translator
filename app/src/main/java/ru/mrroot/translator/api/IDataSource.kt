package ru.mrroot.translator.api

import ru.mrroot.translator.model.entity.dto.WordDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {
    @GET("words/search")
    fun searchAsync(@Query("search") word: String): Deferred<List<WordDTO>>
}