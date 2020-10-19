package ru.geekbrains.kotlin_professional.dictionary.presenter.base

import io.reactivex.Observable
import ru.geekbrains.kotlin_professional.dictionary.model.data.DataModel

interface IInteractor<T : DataModel> {

    fun getData(word: String, fromRemote: Boolean): Observable<T>
}