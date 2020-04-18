package githubexplorer.oscarm.com.myapplication.app

import android.os.IBinder
import androidx.lifecycle.ViewModelProvider
import githubexplorer.oscarm.com.api.GiphyApi
import githubexplorer.oscarm.com.api.data.Gif
import githubexplorer.oscarm.com.api.data.GifDetails
import githubexplorer.oscarm.com.api.data.GifResponse
import githubexplorer.oscarm.com.api.data.GifUrlSource
import githubexplorer.oscarm.com.myapplication.viewmodelbase.ViewModelFactory
import io.reactivex.Observable

class MyAppTestApp : App(), AppComponent {

    override fun onCreate() {
        super.onCreate()
        appComponent = this
    }

    private var giphyApi: GiphyApi = object : GiphyApi {
        override fun search(searchQuery: String): Observable<GifResponse> =
            Observable.just(GifResponse(listOf(
                Gif("ID1", "First Object Title", GifDetails(GifUrlSource(""))),
                Gif("ID2", "Second Object Title", GifDetails(GifUrlSource(""))),
                Gif("ID3", "Third Object Title", GifDetails(GifUrlSource("")))
            )))
    }
    override fun viewModelFactoryProvider(): ViewModelProvider.Factory = ViewModelFactory(giphyApi)

    override fun giphyApi(): GiphyApi  = giphyApi

    override fun inputMethodManager(): InputManager = object: InputManager {
        override fun hideSoftInputFromWindow(binder: IBinder, flags: Int) {}
    }
}