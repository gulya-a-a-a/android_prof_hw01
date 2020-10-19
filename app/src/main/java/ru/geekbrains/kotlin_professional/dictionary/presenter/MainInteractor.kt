package ru.geekbrains.kotlin_professional.dictionary.presenter

import io.reactivex.Observable
import ru.geekbrains.kotlin_professional.dictionary.model.data.DataModel
import ru.geekbrains.kotlin_professional.dictionary.model.data_source.LocalDataSource
import ru.geekbrains.kotlin_professional.dictionary.model.data_source.RemoteDataSource
import ru.geekbrains.kotlin_professional.dictionary.model.repository.Repository
import ru.geekbrains.kotlin_professional.dictionary.presenter.base.IInteractor

class MainInteractor(
    private val remoteRepo: Repository = Repository(RemoteDataSource()),
    private val localRepo: Repository = Repository(LocalDataSource())
) : IInteractor<DataModel> {

    override fun getData(word: String, fromRemote: Boolean): Observable<DataModel> {
        return if (fromRemote)
            remoteRepo.getData(word).map { DataModel.Success(it) }
        else
            localRepo.getData(word).map { DataModel.Success(it) }
    }
}