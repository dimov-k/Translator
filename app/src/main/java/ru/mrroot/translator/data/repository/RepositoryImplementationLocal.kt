package ru.mrroot.translator.data.repository

import ru.mrroot.translator.data.AppState
import ru.mrroot.translator.data.datasource.DataSourceLocal
import ru.mrroot.translator.data.word.Word

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<Word>>) :
    RepositoryLocal<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getAllData(): List<Word> {
        return dataSource.getAllData()
    }
}
