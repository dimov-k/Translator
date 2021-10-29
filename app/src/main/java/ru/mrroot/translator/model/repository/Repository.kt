package ru.mrroot.translator.model.repository

import ru.mrroot.translator.api.IDataSource
import ru.mrroot.translator.model.cache.ICache
import ru.mrroot.translator.model.entity.Word
import ru.mrroot.translator.networkStatus.INetworkStatus
import ru.mrroot.translator.model.entity.room.HistoryEntity
import ru.mrroot.translator.utils.toWord

class Repository(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: ICache
) : IRepository {

    override suspend fun getWords(word: String): List<Word> {
        cache.putHistoryEntity(HistoryEntity(word))

        val result: List<Word>
        if (networkStatus.isOnline()) {
            result = api.searchAsync(word).await().map { it.toWord() }
            cache.putWords(result)
        } else {
            result = cache.getWords(word)
        }

        return result
    }

    override suspend fun getAllHistory(): List<HistoryEntity> = cache.getHistory()

}