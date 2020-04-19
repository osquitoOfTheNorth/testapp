package githubexplorer.oscarm.com.api.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import githubexplorer.oscarm.com.api.data.Gif
import githubexplorer.oscarm.com.api.data.GifDetails
import githubexplorer.oscarm.com.api.data.GifUrlSource

@Entity(tableName = "gif")
data class PersistedGif(
    @PrimaryKey val id: String,
    val title: String? = null,
    @ColumnInfo(name = "gif_url") val gifUrl: String? = null,
    val rating: String? = null,
    @ColumnInfo(name = "username") val userName: String? = null,
    @ColumnInfo(name = "creation_date") val createDate: String? = null,
    val url: String? = null,
    val price: String? = null
) {
    fun toGif() = Gif(
        id,
        title ?: "",
        GifDetails(GifUrlSource(gifUrl ?: "")),
        rating ?: "",
        userName ?: "",
        createDate ?: "",
        url ?: ""
    )
}