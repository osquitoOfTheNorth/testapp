package githubexplorer.oscarm.com.myapplication.detail

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import githubexplorer.oscarm.com.myapplication.rx.DisposableDelegate
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GifDetailPopulationFeature @Inject constructor(
    private val viewModel: DetailFragmentViewModel,
    @GifName private val nameView: TextView,
    @GifRating private val ratingView: TextView,
    @GifView private val imageView: ImageView,
    @GifCreated private val gifCreated: TextView,
    @GifOwner private val ownerView: TextView,
    @GifPrice private val priceView: TextView,
    private val glide: RequestManager
) : DisposableDelegate() {
    override fun start() {
        viewModel.gif.subscribeSafely {
            nameView.text = it.title
            ratingView.text = it.rating
            glide.asGif().load(it.gifUrl).into(imageView)
            //April 19 2020
            gifCreated.text = it.createDate ?: SimpleDateFormat("YYYY MM dd").format(Date(1587324422173))
            ownerView.text = it.userName
            priceView.text = it.price
        }
    }
}