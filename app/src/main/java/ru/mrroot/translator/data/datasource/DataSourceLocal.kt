package ru.mrroot.translator.data.datasource

import ru.mrroot.translator.data.AppState

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getAllData(): T
}