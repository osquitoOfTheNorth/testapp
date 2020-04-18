package githubexplorer.oscarm.com.myapplication.app

import android.app.Activity
import android.app.Application

open class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}


fun Activity.dependencySource() = (application as App).appComponent