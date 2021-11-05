package ru.mrroot.translator.view.history

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.mrroot.translator.data.AppState
import ru.mrroot.translator.utils.parseSearchResults
import ru.mrroot.translator.viewmodel.BaseViewModel

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
    }

    fun getAllData() {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractorAll() }
    }

    private suspend fun startInteractorAll() {
        _mutableLiveData.postValue(parseSearchResults(interactor.getAllData()))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)//Set View to original state in onStop
        super.onCleared()
    }
}