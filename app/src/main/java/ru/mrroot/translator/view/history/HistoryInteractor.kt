package ru.mrroot.translator.view.history

import ru.mrroot.translator.data.AppState
import ru.mrroot.translator.data.repository.Repository
import ru.mrroot.translator.data.repository.RepositoryLocal
import ru.mrroot.translator.data.word.Word
import ru.mrroot.translator.viewmodel.Interactor

class HistoryInteractor(
    private val repositoryRemote: Repository<List<Word>>,
    private val repositoryLocal: RepositoryLocal<List<Word>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }

    suspend fun getAllData(): AppState {
        return AppState.Success(repositoryLocal.getAllData())
    }
}
