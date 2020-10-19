package ru.geekbrains.kotlin_professional.dictionary.model.data

import com.google.gson.annotations.SerializedName

class Translation(
    @field:SerializedName("text") val text: String?
)