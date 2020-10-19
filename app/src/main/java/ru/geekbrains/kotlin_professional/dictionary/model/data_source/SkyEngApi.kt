package ru.geekbrains.kotlin_professional.dictionary.model.data_source

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.kotlin_professional.dictionary.model.data.SearchResult

interface SkyEngApi {
    @GET("words/search")
    fun search(@Query("search") word: String): Observable<List<SearchResult>>
}