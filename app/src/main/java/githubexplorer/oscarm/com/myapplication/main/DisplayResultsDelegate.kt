package githubexplorer.oscarm.com.myapplication.main

import com.bumptech.glide.RequestManager
import githubexplorer.oscarm.com.myapplication.activity.LoadingDialog
import githubexplorer.oscarm.com.myapplication.adapter.BaseAdapter
import githubexplorer.oscarm.com.myapplication.rx.DisposableDelegate
import javax.inject.Inject

class DisplayResultsDelegate @Inject constructor(
    private val viewModel: MainActivityViewModel,
    private val adapter: BaseAdapter<GifViewHolder>,
    private val requestManager: RequestManager,
    private val loadingDialog: LoadingDialog
): DisposableDelegate() {
    override fun start() {
        loadingDialog.show()
        viewModel.findGifs()
        viewModel.findResults
            .map { it.map { GifViewHolder(it, requestManager) } }
            .doOnNext{ loadingDialog.hide() }
            .subscribeSafely(adapter::setData)
    }
}