package githubexplorer.oscarm.com.myapplication.app

import dagger.BindsInstance
import dagger.Component
import githubexplorer.oscarm.com.api.dagger.ApiModule
import githubexplorer.oscarm.com.myapplication.appsource.InputMethodManagerSource
import githubexplorer.oscarm.com.myapplication.appsource.ViewModelSource
import githubexplorer.oscarm.com.myapplication.dagger.AppScope

@AppScope
@Component(modules = [ApiModule::class, AppModule::class])
interface AppComponent : ViewModelSource, InputMethodManagerSource {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AppComponent
    }
}