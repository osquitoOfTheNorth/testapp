package githubexplorer.oscarm.com.myapplication.main

import androidx.lifecycle.viewModelScope
import githubexplorer.oscarm.com.api.GiphyApi
import githubexplorer.oscarm.com.api.data.Gif
import githubexplorer.oscarm.com.api.persistence.GifDatabase
import githubexplorer.oscarm.com.api.persistence.PersistedGif
import githubexplorer.oscarm.com.myapplication.activity.LoadingDialog
import githubexplorer.oscarm.com.myapplication.app.ToastWrapper
import githubexplorer.oscarm.com.myapplication.viewmodelbase.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val giphyService: GiphyApi,
    private val gifDatabase: GifDatabase
) : BaseViewModel() {

    private val _findResults = BehaviorSubject.create<List<Gif>>()
    private val _error =  PublishSubject.create<String>()
    val findResults = _findResults as Observable<List<Gif>>
    val errors = _error as Observable<String>

    fun findGifs() {
        viewModelScope.launch {
            runCatching { giphyService.findGifs() }
                .onFailure {
                    val persistedGifs = gifDatabase.gifDao().getAllGifs()
                    when {
                        persistedGifs.isEmpty() ->  _error.onNext("No Gifs Available For Sale")
                        else -> _findResults.onNext(persistedGifs.map(PersistedGif::toGif))
                    }
                }
                .onSuccess {
                    it.data.map { gifDatabase.gifDao().insert(it.toPersistedGif()) }
                    _findResults.onNext(it.data)
                }
        }
    }

}

