package githubexplorer.oscarm.com.myapplication.viewmodelbase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import githubexplorer.oscarm.com.api.GiphyApi
import githubexplorer.oscarm.com.myapplication.app.ToastWrapper
import githubexplorer.oscarm.com.myapplication.dagger.AppScope
import githubexplorer.oscarm.com.myapplication.main.MainActivityViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(
    private val api: GiphyApi,
    private val toastWrapper: ToastWrapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            MainActivityViewModel(api, toastWrapper) as T
        } else {
            throw IllegalArgumentException("Cannot find constructor in view model factory ")
        }
}