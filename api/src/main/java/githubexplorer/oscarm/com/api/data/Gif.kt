package githubexplorer.oscarm.com.api.data

import com.squareup.moshi.Json
import githubexplorer.oscarm.com.api.persistence.PersistedGif
import kotlin.random.Random.Default.nextInt

data class Gif(
    val id: String,
    val title: String,
    val images: GifDetails,
    val rating: String,
    val username: String,
    val create_datetime: String,
    @Json(name="bitly_url") val url: String

) {
    fun priceString() = "$5"
    fun displayRating() = rating.toUpperCase()

    fun toPersistedGif() = PersistedGif(
        id, title, images.fixed_height_downsampled.url, rating, username, create_datetime, url, priceString()
    )
}

data class GifDetails(val fixed_height_downsampled : GifUrlSource)

data class GifUrlSource(val url: String)

data class GifResponse(val data: List<Gif>)
