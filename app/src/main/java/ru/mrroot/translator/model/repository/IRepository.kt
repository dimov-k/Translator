package ru.mrroot.translator.model.repository

import ru.mrroot.translator.model.entity.Word
import ru.mrroot.translator.model.entity.room.HistoryEntity

interface IRepository {
    suspend fun getWords(word: String): List<Word>
    suspend fun getAllHistory(): List<HistoryEntity>
}