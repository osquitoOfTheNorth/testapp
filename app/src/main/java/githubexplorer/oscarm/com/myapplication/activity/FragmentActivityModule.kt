package githubexplorer.oscarm.com.myapplication.activity

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import githubexplorer.oscarm.com.myapplication.dagger.ActivityScope

@Module
class FragmentActivityModule {

    @Provides
    @ActivityScope
    fun fragmentActivity(activity: Activity): FragmentActivity = activity as FragmentActivity

    @Provides
    @ActivityScope
    fun supportFragmentManager(fragmentActivity: FragmentActivity): FragmentManager =
        fragmentActivity.supportFragmentManager

}