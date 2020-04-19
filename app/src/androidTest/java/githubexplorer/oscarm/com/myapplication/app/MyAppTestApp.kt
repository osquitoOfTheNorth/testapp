package githubexplorer.oscarm.com.myapplication.app

import androidx.lifecycle.ViewModelProvider
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import githubexplorer.oscarm.com.api.GiphyApi
import githubexplorer.oscarm.com.api.data.Gif
import githubexplorer.oscarm.com.api.data.GifDetails
import githubexplorer.oscarm.com.api.data.GifResponse
import githubexplorer.oscarm.com.api.data.GifUrlSource
import githubexplorer.oscarm.com.api.persistence.GifDao
import githubexplorer.oscarm.com.api.persistence.GifDatabase
import githubexplorer.oscarm.com.api.persistence.PersistedGif
import githubexplorer.oscarm.com.myapplication.viewmodelbase.ViewModelFactory
import kotlinx.coroutines.runBlocking
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MyAppTestApp : App(), AppComponent {

    private val appRef = this
    override fun onCreate() {
        super.onCreate()
        appComponent = this
    }

    private var giphyApi: GiphyApi = object : GiphyApi {
        override suspend fun findGifs() =
            GifResponse(listOf(
                Gif("ID1", "First Object Title", GifDetails(GifUrlSource("")), "R", "Test", "", ""),
                Gif("ID2", "Second Object Title", GifDetails(GifUrlSource("")), "pg-13", "Test2" , "", ""),
                Gif("ID3", "Third Object Title", GifDetails(GifUrlSource("")), "g", "TEST3", "", "")
            ))
    }

    private val database: GifDatabase = object: GifDatabase() {
        override fun gifDao(): GifDao {
            val mockDao = mock(GifDao::class.java)
            `when`(runBlocking { mockDao.getGifs("ID1") }).thenReturn(
                PersistedGif(
                    "ID1",
                    "First Object Title",
                    "",
                    "R",
                    "Test",
                    "",
                    "",
                    "$2"
                )
            )
            return mockDao
        }

        override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
            return FrameworkSQLiteOpenHelperFactory().create(SupportSQLiteOpenHelper.Configuration.builder(appRef).build())
        }

        override fun createInvalidationTracker(): InvalidationTracker {
           return InvalidationTracker(this, "gifs")
        }

        override fun clearAllTables()  = Unit
    }

    override fun viewModelFactoryProvider(): ViewModelProvider.Factory =
        ViewModelFactory(giphyApi, database)

}