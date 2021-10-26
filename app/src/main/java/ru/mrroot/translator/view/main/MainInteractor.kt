package ru.mrroot.translator.view.main

import ru.mrroot.translator.di.NAME_LOCAL
import ru.mrroot.translator.di.NAME_REMOTE
import ru.mrroot.translator.model.data.AppState
import ru.mrroot.translator.model.data.DataModel
import ru.mrroot.translator.model.repository.Repository
import ru.mrroot.translator.viewModel.Interactor
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
       return if (fromRemoteSource) {
           repositoryRemote.getData(word).map { AppState.Success(it) }
        } else {
           repositoryLocal.getData(word).map{AppState.Success(it)}
        }
    }
}