package githubexplorer.oscarm.com.myapplication.detail

import githubexplorer.oscarm.com.myapplication.rx.PlainDelegate
import javax.inject.Inject

class GetGifInfoDelegate @Inject constructor(
    private val viewModel: DetailFragmentViewModel,
    @GifId private val  id: String
) : PlainDelegate() {
    override fun start() { viewModel.getGifs(id) }
}