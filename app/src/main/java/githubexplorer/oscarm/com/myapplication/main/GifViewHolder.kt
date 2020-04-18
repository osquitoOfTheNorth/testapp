package githubexplorer.oscarm.com.myapplication.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import githubexplorer.oscarm.com.api.data.Gif
import githubexplorer.oscarm.com.myapplication.R
import githubexplorer.oscarm.com.myapplication.adapter.ViewHolderBinding

class GifViewHolder(
    private val gif: Gif,
    private val glide: RequestManager
) : ViewHolderBinding {
    override val viewType = 1
    override val id = gif.id

    override fun bind(view: View) {
       view.findViewById<TextView>(R.id.gifName).text= gif.title
        val imageView = view.findViewById<ImageView>(R.id.gifImage)
        glide.asGif().load(gif.images.fixed_height_downsampled.url).into(imageView)
    }
}