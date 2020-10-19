package ru.geekbrains.kotlin_professional.dictionary.model.data

import com.google.gson.annotations.SerializedName

class SearchResult(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meaning>?
)