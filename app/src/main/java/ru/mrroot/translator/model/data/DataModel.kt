package ru.mrroot.translator.model.data

import com.google.gson.annotations.SerializedName

class DataModel(
    @SerializedName("text") val text: String?,
    @SerializedName("meanings") val meanings: List<Meanings>?
)