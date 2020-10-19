package ru.geekbrains.kotlin_professional.dictionary.model.repository

import io.reactivex.Observable
import ru.geekbrains.kotlin_professional.dictionary.model.data.SearchResult
import ru.geekbrains.kotlin_professional.dictionary.model.data_source.IDataSource

class Repository(val dataSource: IDataSource<List<SearchResult>>) :
    IRepository<List<SearchResult>> {

    override fun getData(word: String): Observable<List<SearchResult>> {
        return dataSource.getData(word)
    }
}