package ru.geekbrains.kotlin_professional.dictionary.view.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.geekbrains.kotlin_professional.dictionary.R
import ru.geekbrains.kotlin_professional.dictionary.model.data.DataModel
import ru.geekbrains.kotlin_professional.dictionary.model.data.SearchResult
import ru.geekbrains.kotlin_professional.dictionary.presenter.MainPresenter
import ru.geekbrains.kotlin_professional.dictionary.presenter.base.IPresenter
import ru.geekbrains.kotlin_professional.dictionary.view.base.BaseActivity
import ru.geekbrains.kotlin_professional.dictionary.view.base.IView
import ru.geekbrains.kotlin_professional.dictionary.view.fragments.SearchDialogFragment
import ru.geekbrains.kotlin_professional.dictionary.view.recycler.SearchResultAdapter
import java.lang.Error

class MainActivity : BaseActivity<DataModel>() {

    private var _adapter: SearchResultAdapter? = null

    private val _onItemClickListener: SearchResultAdapter.OnItemClickListener =
        object : SearchResultAdapter.OnItemClickListener {
            override fun onClick(item: SearchResult) {
                Toast.makeText(this@MainActivity, item.text, Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_search?.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.createInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(text: String) {
                    _presenter.getData(text, true)
                }
            })
            searchDialogFragment.show(supportFragmentManager, "SEARCH_FRAGMENT")
        }
    }

    override fun createPresenter(): IPresenter<DataModel, IView> {
        return MainPresenter()
    }

    override fun showData(dataModel: DataModel) {
        when (dataModel) {
            is DataModel.Success -> {
                showResultsLayout()

                if (dataModel.data.isEmpty())
                    showError(DataModel.Error(Error("No translation found")))

                if (_adapter == null) {
                    _adapter = SearchResultAdapter(_onItemClickListener, dataModel.data)
                    rv_search_results.layoutManager = LinearLayoutManager(applicationContext)
                    rv_search_results.adapter = _adapter
                } else {
                    _adapter!!.setData(dataModel.data)

                }
            }
            is DataModel.Loading -> {
                showLoadingLayout()
            }
            is DataModel.Error -> {
                showError(dataModel)
            }
        }
    }

    private fun showError(dataModel: DataModel.Error) {
        showErrorLayout()
        tv_error_message.text = dataModel.error.message
    }

    private fun showResultsLayout() {
        layout_loading.visibility = GONE
        layout_error.visibility = GONE
        layout_results.visibility = VISIBLE
    }

    private fun showLoadingLayout() {
        layout_results.visibility = GONE
        layout_error.visibility = GONE
        layout_loading.visibility = VISIBLE
    }

    private fun showErrorLayout() {
        layout_results.visibility = GONE
        layout_loading.visibility = GONE
        layout_error.visibility = VISIBLE
    }
}