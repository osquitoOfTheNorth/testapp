package githubexplorer.oscarm.com.myapplication.viewmodelbase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import githubexplorer.oscarm.com.api.GiphyApi
import githubexplorer.oscarm.com.api.persistence.GifDatabase
import githubexplorer.oscarm.com.myapplication.app.ToastWrapper
import githubexplorer.oscarm.com.myapplication.dagger.AppScope
import githubexplorer.oscarm.com.myapplication.detail.DetailFragmentViewModel
import githubexplorer.oscarm.com.myapplication.main.MainActivityViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(
    private val api: GiphyApi,
    private val database: GifDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) ->
                MainActivityViewModel(api, database) as T
            modelClass.isAssignableFrom(DetailFragmentViewModel::class.java) ->
                DetailFragmentViewModel(database) as T
            else -> throw IllegalArgumentException("Wrong View Model Type")
        }
}