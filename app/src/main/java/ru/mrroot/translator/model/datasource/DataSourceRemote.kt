package ru.mrroot.translator.model.datasource

import ru.mrroot.translator.model.data.DataModel
import io.reactivex.Observable

class DataSourceRemote (private val remoteProvider: RetrofitImpl = RetrofitImpl()) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}