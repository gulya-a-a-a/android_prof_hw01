package ru.geekbrains.kotlin_professional.dictionary.model.data_source

import io.reactivex.Observable

interface IDataSource<T> {
    fun getData(word: String): Observable<T>
}