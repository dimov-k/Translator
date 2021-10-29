package ru.mrroot.translator.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mrroot.translator.model.entity.room.HistoryEntity
import ru.mrroot.translator.model.entity.room.RoomTranslation
import ru.mrroot.translator.model.entity.room.RoomWord

@Database(entities = [HistoryEntity::class, RoomWord::class, RoomTranslation::class], version = 2, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}
