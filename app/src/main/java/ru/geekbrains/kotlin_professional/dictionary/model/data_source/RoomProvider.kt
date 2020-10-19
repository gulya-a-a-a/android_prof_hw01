package ru.geekbrains.kotlin_professional.dictionary.model.data_source

import io.reactivex.Observable
import ru.geekbrains.kotlin_professional.dictionary.model.data.SearchResult

class RoomProvider : IDataSource<List<SearchResult>> {
    override fun getData(word: String): Observable<List<SearchResult>> {
        TODO("Not yet implemented")
    }
}