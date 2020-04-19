package githubexplorer.oscarm.com.myapplication.detail

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import githubexplorer.oscarm.com.myapplication.R
import githubexplorer.oscarm.com.myapplication.app.dependencySource
import githubexplorer.oscarm.com.myapplication.appsource.ViewModelSource
import githubexplorer.oscarm.com.myapplication.dagger.BaseComponent
import githubexplorer.oscarm.com.myapplication.dagger.FragmentScope
import githubexplorer.oscarm.com.myapplication.delegates.CompositeDelegates
import githubexplorer.oscarm.com.myapplication.delegates.Delegate
import githubexplorer.oscarm.com.myapplication.fragment.DelegatingFragment
import kotlinx.android.synthetic.main.detail_fragment_layout.*
import javax.inject.Qualifier

private val ARG_GIF_ID = "GIF_ID"

class GifDetailFragment : DelegatingFragment() {

    companion object {

        fun newInstance(id: String) = GifDetailFragment()
            .apply {
            arguments = Bundle().apply { putString(ARG_GIF_ID, id) }
        }
    }

    override fun viewResourceId(): Int = R.layout.detail_fragment_layout

    override fun createDelegates(): Delegate =
        DaggerGifDetailComponent.factory().create(
            this,
                price,
                rating,
                gifName,
                gifImage,
                created,
                owned,
                source,
                activity!!.dependencySource()
        ).delegates()
}

@Component(modules = [GifDetailModule::class], dependencies = [ViewModelSource::class])
@FragmentScope
interface GifDetailComponent : BaseComponent {

    @Component.Factory
    interface GifDetailComponentFactory {
        fun create(
            @BindsInstance fragment: Fragment,
            @BindsInstance @GifPrice textView: TextView,
            @BindsInstance @GifRating ratingView: TextView,
            @BindsInstance @GifName nameView: TextView,
            @BindsInstance @GifView givView: ImageView,
            @BindsInstance @GifCreated gifCreated: TextView,
            @BindsInstance @GifOwner ownerView: TextView,
            @BindsInstance @GifSource source: TextView,
            viewModelSource: ViewModelSource
        ): GifDetailComponent
    }
}

@Module
class GifDetailModule {

    @Provides
    @FragmentScope
    fun glide(ctx: Fragment): RequestManager = Glide.with(ctx)

    @Provides
    fun delegates(
            loadGifDelegate: GetGifInfoDelegate,
            popFeature: GifDetailPopulationFeature
    ): Delegate = CompositeDelegates(loadGifDelegate, popFeature)

    @Provides
    @GifId
    fun gifId(fragment: Fragment) = fragment.arguments!!.getString(ARG_GIF_ID)!!

    @Provides
    @FragmentScope
    fun vm(vmFactoryProvider: ViewModelProvider.Factory): DetailFragmentViewModel =
        vmFactoryProvider.create(DetailFragmentViewModel::class.java)
}

@Qualifier annotation class GifId
@Qualifier annotation class GifPrice
@Qualifier annotation class GifRating
@Qualifier annotation class GifName
@Qualifier annotation class GifView
@Qualifier annotation class GifCreated
@Qualifier annotation class GifOwner
@Qualifier annotation class GifSource
