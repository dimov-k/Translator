package ru.mrroot.translator.view.main

import ru.mrroot.translator.data.AppState
import ru.mrroot.translator.data.repository.Repository
import ru.mrroot.translator.data.repository.RepositoryLocal
import ru.mrroot.translator.data.word.Word
import ru.mrroot.translator.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<Word>>,
    private val repositoryLocal: RepositoryLocal<List<Word>>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}