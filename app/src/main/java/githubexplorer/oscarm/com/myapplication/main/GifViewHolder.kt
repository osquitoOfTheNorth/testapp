package githubexplorer.oscarm.com.myapplication.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import githubexplorer.oscarm.com.api.data.Gif
import githubexplorer.oscarm.com.myapplication.R
import githubexplorer.oscarm.com.myapplication.adapter.ViewHolderBinding
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class GifViewHolder(
    private val gif: Gif,
    private val glide: RequestManager
) : ViewHolderBinding {
    override val viewType = 1
    override val id = gif.id
    private val clicksSubject = PublishSubject.create<String>()

    override fun bind(view: View): Observable<String>{
        val textView = view.findViewById<TextView>(R.id.gifName)
        val price = view.findViewById<TextView>(R.id.price)
        val rating = view.findViewById<TextView>(R.id.rating)
        val imageView = view.findViewById<ImageView>(R.id.gifImage)
        glide.asGif().load(gif.images.fixed_height_downsampled.url).into(imageView)
        textView.text = gif.title
        price.text = gif.priceString()
        rating.text = gif.displayRating()
        view.setOnClickListener { clicksSubject.onNext(gif.id) }
        return clicksSubject
    }
}