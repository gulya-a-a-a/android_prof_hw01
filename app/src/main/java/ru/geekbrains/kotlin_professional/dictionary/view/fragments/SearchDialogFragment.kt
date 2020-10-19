package ru.geekbrains.kotlin_professional.dictionary.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.search_fragment.*
import ru.geekbrains.kotlin_professional.dictionary.R

class SearchDialogFragment : BottomSheetDialogFragment() {

    private lateinit var etSearchInput: TextInputEditText
    private lateinit var btnStartSearch: Button

    private var m_onSearchClickListener: OnSearchClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etSearchInput = et_search_input
        btnStartSearch = btn_start_search
        btnStartSearch.setOnClickListener {
            m_onSearchClickListener?.onClick(etSearchInput.text.toString())
        }

        super.onViewCreated(view, savedInstanceState)
    }

    fun setOnSearchClickListener(listener: OnSearchClickListener) {
        m_onSearchClickListener = listener
    }

    interface OnSearchClickListener {
        fun onClick(text: String)
    }

    companion object {
        fun createInstance(): SearchDialogFragment {
            return SearchDialogFragment()
        }
    }
}