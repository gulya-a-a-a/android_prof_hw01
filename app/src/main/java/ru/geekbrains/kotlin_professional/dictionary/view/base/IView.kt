package ru.geekbrains.kotlin_professional.dictionary.view.base

import ru.geekbrains.kotlin_professional.dictionary.model.data.DataModel

interface IView {
    fun showData(dataModel: DataModel)
}