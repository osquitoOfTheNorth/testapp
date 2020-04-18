package githubexplorer.oscarm.com.myapplication.main

import androidx.lifecycle.viewModelScope
import githubexplorer.oscarm.com.api.GiphyApi
import githubexplorer.oscarm.com.api.data.Gif
import githubexplorer.oscarm.com.myapplication.app.ToastWrapper
import githubexplorer.oscarm.com.myapplication.base.Either
import githubexplorer.oscarm.com.myapplication.viewmodelbase.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val gipyService: GiphyApi,
    private val toastWrapper: ToastWrapper
) : BaseViewModel() {

    private val _searchResults = BehaviorSubject.create<Either<Throwable, List<Gif>>>()
    val searchResults = _searchResults as Observable<Either<Throwable, List<Gif>>>

    fun searchForGifs(searchPhrase: String) {
        viewModelScope.launch {
           runCatching { gipyService.search(searchPhrase) }
                .onSuccess { _searchResults.onNext(Either.Right(it.data)) }
                .onFailure {
                    _searchResults.onNext(Either.Left(it))
                    toastWrapper.showToast("Sorry, an error occured!")
                }
        }
    }

}

