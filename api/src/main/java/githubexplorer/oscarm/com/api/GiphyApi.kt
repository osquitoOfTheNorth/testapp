package githubexplorer.oscarm.com.api

import githubexplorer.oscarm.com.api.data.GifResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("v1/gifs/search")
    suspend fun search(@Query("q") searchQuery: String): GifResponse
}