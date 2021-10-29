package ru.mrroot.translator.utils

import android.text.TextUtils
import ru.mrroot.translator.model.entity.Translation
import ru.mrroot.translator.model.entity.Word
import ru.mrroot.translator.model.entity.dto.MeaningsDTO
import ru.mrroot.translator.model.entity.dto.WordDTO
import ru.mrroot.translator.model.entity.room.RoomTranslation
import ru.mrroot.translator.model.entity.room.RoomWord
import ru.mrroot.translator.model.entity.room.RoomWordWithTranslations

fun WordDTO.toWord() = Word(id, text, meaningsDTO?.map { it.toTranslation() })

fun MeaningsDTO.toTranslation() = Translation(id, translationDTO?.text, imageUrl, transcription)

fun Word.toRoomWord() = RoomWord(id, text)

fun Translation.toRoomTranslation(wordId: Int) =
    RoomTranslation(id, wordId, translation, imageUrl, transcription)

fun RoomWordWithTranslations.toWord() =
    Word(word.id, word.text, translations.map { it.toTranslation() })

fun RoomTranslation.toTranslation() = Translation(id, translation, imageUrl, transcription)

fun Word.getTranslationsString(): String{
    val translStringList = translations?.map { it.translation } ?: listOf()
    return TextUtils.join(", ", translStringList)
}