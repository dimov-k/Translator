package ru.mrroot.translator.model.repository

import ru.mrroot.translator.model.data.DataModel
import ru.mrroot.translator.model.datasource.DataSource
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl (private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}