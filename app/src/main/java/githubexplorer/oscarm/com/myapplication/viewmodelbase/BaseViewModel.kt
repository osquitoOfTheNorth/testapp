package githubexplorer.oscarm.com.myapplication.viewmodelbase

import androidx.lifecycle.ViewModel
import githubexplorer.oscarm.com.myapplication.rx.DisposableContainer
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(), DisposableContainer {
    override val disposableContainer: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        disposableContainer.clear()
    }
}