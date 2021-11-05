package ru.mrroot.translator.data.datasource

interface DataSource<T> {

    suspend fun getData(word: String): T
}
