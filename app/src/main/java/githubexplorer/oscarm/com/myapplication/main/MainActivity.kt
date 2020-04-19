package githubexplorer.oscarm.com.myapplication.main

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import githubexplorer.oscarm.com.myapplication.activity.DelegatingActivity
import githubexplorer.oscarm.com.myapplication.R
import githubexplorer.oscarm.com.myapplication.activity.FragmentActivityModule
import githubexplorer.oscarm.com.myapplication.adapter.BaseAdapter
import githubexplorer.oscarm.com.myapplication.app.dependencySource
import githubexplorer.oscarm.com.myapplication.appsource.InputMethodManagerSource
import githubexplorer.oscarm.com.myapplication.appsource.ViewModelSource
import githubexplorer.oscarm.com.myapplication.dagger.ActivityScope
import githubexplorer.oscarm.com.myapplication.dagger.AppCompatModule
import githubexplorer.oscarm.com.myapplication.dagger.BaseComponent
import githubexplorer.oscarm.com.myapplication.dagger.LoadingDialogModule
import githubexplorer.oscarm.com.myapplication.delegates.Delegate
import githubexplorer.oscarm.com.myapplication.delegates.CompositeDelegates
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DelegatingActivity() {

    override fun createDelegates(): Delegate {
        setContentView(R.layout.activity_main)
        return DaggerMainActivityComponent.factory()
            .create(
                dependencySource(),
                this,
                resultsListView
            ).delegates()
    }

}

@Component(
    dependencies = [ViewModelSource::class],
    modules = [MainActivityModule::class, FragmentActivityModule::class]
)
@ActivityScope
interface MainActivityComponent : BaseComponent {
    @Component.Factory
    interface Factory {
        fun create(
            viewModelSource: ViewModelSource,
            @BindsInstance activity: Activity,
            @BindsInstance recyclerView: RecyclerView
        ): MainActivityComponent
    }
}

@Module(includes = [AppCompatModule::class, LoadingDialogModule::class])
class MainActivityModule {
    @Provides
    fun delegates(
        displayResultsDelegate: DisplayResultsDelegate,
        errors: FindGifsErrorFeature,
        gifClick: GifClickDelegate
    ): Delegate =
        CompositeDelegates(displayResultsDelegate, errors, gifClick)

    @Provides
    @ActivityScope
    fun adapter(
        recyclerView: RecyclerView,
        inflater: LayoutInflater,
        ctx: Context
    ) = BaseAdapter<GifViewHolder>(R.layout.gif_overview_layout, inflater).apply {
        recyclerView.adapter = this
        recyclerView.layoutManager = LinearLayoutManager(ctx)
    }

    @Provides
    @ActivityScope
    fun ctx(activity: Activity): Context = activity

    @Provides
    fun layoutInflater(ctx: Context): LayoutInflater = LayoutInflater.from(ctx)

    @Provides
    @ActivityScope
    fun vm(vmFactoryProvider: ViewModelProvider.Factory): MainActivityViewModel =
        vmFactoryProvider.create(MainActivityViewModel::class.java)

    @Provides
    @ActivityScope
    fun glide(ctx: Activity): RequestManager = Glide.with(ctx)
}