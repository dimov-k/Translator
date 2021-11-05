package ru.mrroot.translator.data.datasource

import ru.mrroot.translator.data.AppState
import ru.mrroot.translator.data.room.HistoryDao
import ru.mrroot.translator.data.word.Word
import ru.mrroot.translator.utils.convertDataModelSuccessToEntities
import ru.mrroot.translator.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return mapHistoryEntityToSearchResult(historyDao.getDataByWord(word))
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntities(appState)?.let {
            historyDao.insertAll(it)
        }
    }

    override suspend fun getAllData(): List<Word> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }
}