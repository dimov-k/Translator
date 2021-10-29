package ru.mrroot.translator.viewModel

interface Interactor<T> {
    suspend fun getData(word: String): T
}