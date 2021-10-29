package ru.mrroot.translator.model.cache

import ru.mrroot.translator.model.entity.Word
import ru.mrroot.translator.model.entity.room.RoomTranslation
import ru.mrroot.translator.room.HistoryDao
import ru.mrroot.translator.model.entity.room.HistoryEntity
import ru.mrroot.translator.utils.toRoomTranslation
import ru.mrroot.translator.utils.toRoomWord
import ru.mrroot.translator.utils.toWord

class Cache(private val dao: HistoryDao) : ICache {
    override suspend fun getWords(word: String): List<Word> {
        return dao.getWordAdvanced(word).map { it.toWord() }
    }

    override suspend fun putWords(words: List<Word>) {
        val roomWords = words.map { it.toRoomWord() }
        val roomTranslations: MutableList<RoomTranslation> = mutableListOf()

        for (word in words) {
            word.translations?.map { it.toRoomTranslation(word.id) }?.let {
                roomTranslations.addAll(it)
            }
        }

        dao.insertWordsWithTranslations(roomWords, roomTranslations)
    }

    override suspend fun getHistory(): List<HistoryEntity> = dao.getAllHistory()

    override suspend fun putHistoryEntity(historyEntity: HistoryEntity) {
        dao.insert(historyEntity)
    }
}