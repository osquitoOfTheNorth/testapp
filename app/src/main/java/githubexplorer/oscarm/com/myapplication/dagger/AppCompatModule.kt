package githubexplorer.oscarm.com.myapplication.dagger

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class AppCompatModule {
    @Provides
    fun appCompatActivity(activity: Activity) : AppCompatActivity = activity as AppCompatActivity
}