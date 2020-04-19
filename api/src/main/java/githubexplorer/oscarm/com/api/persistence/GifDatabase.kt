package githubexplorer.oscarm.com.api.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(PersistedGif::class), version = 1, exportSchema = false)
public abstract class GifDatabase : RoomDatabase() {
    abstract fun gifDao(): GifDao
}