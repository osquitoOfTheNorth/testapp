package githubexplorer.oscarm.com.myapplication.main

import githubexplorer.oscarm.com.myapplication.activity.ActivityNavigation
import githubexplorer.oscarm.com.myapplication.adapter.BaseAdapter
import githubexplorer.oscarm.com.myapplication.detail.GifDetailFragment
import githubexplorer.oscarm.com.myapplication.rx.DisposableDelegate
import javax.inject.Inject

class GifClickDelegate @Inject constructor(
    private val adapter: BaseAdapter<GifViewHolder>,
    private val activityNavigation: ActivityNavigation
) : DisposableDelegate() {
    override fun start() {
        adapter.clicks.ofType(String::class.java).subscribeSafely {
           activityNavigation.add(GifDetailFragment.newInstance(it), "Tag")
        }
    }
}