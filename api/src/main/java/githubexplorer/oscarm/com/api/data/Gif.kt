package githubexplorer.oscarm.com.api.data

data class Gif(
    val id: String,
    val title: String,
    val images: GifDetails
)

data class GifDetails(val fixed_height_downsampled : GifUrlSource)

data class GifUrlSource(val url: String)

data class GifResponse(val data: List<Gif>)
