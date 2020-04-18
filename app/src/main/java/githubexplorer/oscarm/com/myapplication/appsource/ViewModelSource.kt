package githubexplorer.oscarm.com.myapplication.appsource

import androidx.lifecycle.ViewModelProvider

interface ViewModelSource {
    fun viewModelFactoryProvider() : ViewModelProvider.Factory
}