package githubexplorer.oscarm.com.myapplication.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import githubexplorer.oscarm.com.api.data.Gif
import githubexplorer.oscarm.com.api.persistence.GifDatabase
import githubexplorer.oscarm.com.api.persistence.PersistedGif
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

open class DetailFragmentViewModel(private val database: GifDatabase) : ViewModel() {

    private val _gif = BehaviorSubject.create<PersistedGif>()
    open val gif = _gif as Observable<PersistedGif>

    fun getGifs(id: String) = viewModelScope.launch {
        val gif = database.gifDao().getGifs(id)
        _gif.onNext(gif)
    }
}