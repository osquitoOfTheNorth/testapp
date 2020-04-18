package githubexplorer.oscarm.com.myapplication.main

import com.bumptech.glide.RequestManager
import com.google.android.material.appbar.AppBarLayout
import githubexplorer.oscarm.com.api.data.Gif
import githubexplorer.oscarm.com.api.data.GifResponse
import githubexplorer.oscarm.com.myapplication.activity.LoadingDialog
import githubexplorer.oscarm.com.myapplication.adapter.BaseAdapter
import githubexplorer.oscarm.com.myapplication.base.Either
import githubexplorer.oscarm.com.myapplication.rx.DisposableDelegate
import javax.inject.Inject

class DisplaySearchResultsDelegate @Inject constructor(
    private val viewModel: MainActivityViewModel,
    private val loadingDialog: LoadingDialog,
    private val adapter: BaseAdapter<GifViewHolder>,
    private val requestManager: RequestManager,
    private val appBarLayout: AppBarLayout
): DisposableDelegate() {
    override fun start() {
        viewModel.searchResults
            .doOnNext { loadingDialog.hide() }
            .filter { it.isRight }
            .map { (it as Either.Right<List<Gif>>).value }
            .map { it.map { GifViewHolder(it, requestManager) } }
            .subscribeSafely {
                adapter.setData(it)
                appBarLayout.setExpanded(false)
            }
    }
}