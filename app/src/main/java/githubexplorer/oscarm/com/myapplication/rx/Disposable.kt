package githubexplorer.oscarm.com.myapplication.rx

import githubexplorer.oscarm.com.myapplication.delegates.ActivityDelegate
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

abstract class DisposableDelegate : ActivityDelegate, DisposableContainer {
    override val disposableContainer: CompositeDisposable = CompositeDisposable()

    override fun stop() = disposableContainer.clear()
}


interface DisposableContainer {
    val disposableContainer: CompositeDisposable

    fun <T> Observable<T>.subscribeSafely(onNext: (T) -> Unit) =
        disposableContainer.add(this.subscribe(onNext))

}