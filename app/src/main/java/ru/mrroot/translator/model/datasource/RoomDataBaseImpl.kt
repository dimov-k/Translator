package ru.mrroot.translator.model.datasource

import ru.mrroot.translator.model.data.DataModel
import io.reactivex.Observable
import javax.inject.Inject

class RoomDataBaseImpl: DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}