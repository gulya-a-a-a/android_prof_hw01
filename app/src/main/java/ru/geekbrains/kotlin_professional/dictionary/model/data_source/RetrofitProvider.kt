package ru.geekbrains.kotlin_professional.dictionary.model.data_source

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.kotlin_professional.dictionary.model.data.SearchResult

class RetrofitProvider() : IDataSource<List<SearchResult>> {

    companion object {
        private val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    }

    val m_api: SkyEngApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        .create(SkyEngApi::class.java)

    override fun getData(word: String): Observable<List<SearchResult>> {
        return m_api.search(word)
    }

}