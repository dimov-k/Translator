package ru.mrroot.translator.model.cache

import ru.mrroot.translator.model.entity.Word
import ru.mrroot.translator.model.entity.room.HistoryEntity

interface ICache {
    suspend fun getWords(word: String): List<Word>
    suspend fun putWords(words: List<Word>)
    suspend fun getHistory(): List<HistoryEntity>
    suspend fun putHistoryEntity(historyEntity: HistoryEntity)
}