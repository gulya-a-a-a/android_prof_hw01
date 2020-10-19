package ru.geekbrains.kotlin_professional.dictionary.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_recycler_item.view.*
import ru.geekbrains.kotlin_professional.dictionary.R
import ru.geekbrains.kotlin_professional.dictionary.model.data.SearchResult

class SearchResultAdapter(
    private var m_onItemClickListener: OnItemClickListener,
    private var m_dataList: List<SearchResult>
) : RecyclerView.Adapter<SearchResultAdapter.ResultHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        return ResultHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.search_recycler_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return m_dataList.size
    }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        holder.bind(m_dataList[position])
    }

    fun setData(list: List<SearchResult>) {
        m_dataList = list
        notifyDataSetChanged()
    }

    inner class ResultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: SearchResult) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.tv_translation.text = item.text
                itemView.tv_meaning.text = item.meanings?.get(0)?.translation?.text
            }

            itemView.setOnClickListener {
                m_onItemClickListener.onClick(item)
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(item: SearchResult)
    }
}