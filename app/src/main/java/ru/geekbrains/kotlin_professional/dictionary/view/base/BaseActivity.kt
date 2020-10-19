package ru.geekbrains.kotlin_professional.dictionary.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.kotlin_professional.dictionary.model.data.DataModel
import ru.geekbrains.kotlin_professional.dictionary.presenter.base.IPresenter

abstract class BaseActivity<T : DataModel> : AppCompatActivity(), IView {

    protected lateinit var _presenter: IPresenter<T, IView>

    protected abstract fun createPresenter(): IPresenter<T, IView>

    abstract override fun showData(dataModel: DataModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        _presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        _presenter.detachView(this)
    }
}