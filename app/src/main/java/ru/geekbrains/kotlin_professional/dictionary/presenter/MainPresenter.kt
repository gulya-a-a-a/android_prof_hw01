package ru.geekbrains.kotlin_professional.dictionary.presenter

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import ru.geekbrains.kotlin_professional.dictionary.model.data.DataModel
import ru.geekbrains.kotlin_professional.dictionary.presenter.base.IPresenter
import ru.geekbrains.kotlin_professional.dictionary.view.base.IView

class MainPresenter(
    private val m_interactor: MainInteractor = MainInteractor(),
    private var m_compositeDisposable: CompositeDisposable = CompositeDisposable()
) : IPresenter<DataModel, IView> {

    private var m_view: IView? = null

    override fun attachView(view: IView) {
        if (m_view != view) {
            m_view = view;
        }
    }

    override fun detachView(view: IView) {
        m_compositeDisposable.dispose()
        if (view == m_view)
            m_view = null
    }

    override fun getData(text: String, isOnline: Boolean) {
        m_compositeDisposable.add(
            m_interactor.getData(text, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(Consumer { m_view?.showData(DataModel.Loading(null)) })
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<DataModel> {
        return object : DisposableObserver<DataModel>() {
            override fun onNext(t: DataModel) {
                m_view?.showData(t)
            }

            override fun onError(e: Throwable) {
                m_view?.showData(DataModel.Error(e))
            }

            override fun onComplete() {}
        }
    }
}