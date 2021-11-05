package ru.mrroot.translator.data.word

import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("text") val text: String,
    @SerializedName("note") val note: String
)
