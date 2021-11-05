package ru.mrroot.translator.data.repository

import ru.mrroot.translator.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getAllData(): T
}