package githubexplorer.oscarm.com.myapplication.dagger

import android.app.Activity
import dagger.Module
import dagger.Provides
import githubexplorer.oscarm.com.myapplication.activity.LoadingDialog

@Module
class LoadingDialogModule {
    @Provides
    @ActivityScope
    fun dialog(activity: Activity) = LoadingDialog(activity)
}
