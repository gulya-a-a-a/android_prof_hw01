package ru.geekbrains.kotlin_professional.dictionary.presenter.base

import ru.geekbrains.kotlin_professional.dictionary.model.data.DataModel
import ru.geekbrains.kotlin_professional.dictionary.view.base.IView

interface IPresenter<T : DataModel, V : IView> {

    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(text: String, isOnline: Boolean)
}