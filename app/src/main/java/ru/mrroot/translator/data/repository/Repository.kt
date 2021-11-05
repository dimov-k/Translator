package ru.mrroot.translator.data.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}