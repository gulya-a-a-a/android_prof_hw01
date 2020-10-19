package ru.geekbrains.kotlin_professional.dictionary.model.data

import com.google.gson.annotations.SerializedName

class Meaning(
    @field:SerializedName("translation") val translation: Translation
)