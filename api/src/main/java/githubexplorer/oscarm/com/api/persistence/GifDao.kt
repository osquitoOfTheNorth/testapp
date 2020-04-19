package githubexplorer.oscarm.com.api.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GifDao {

    @Query("SELECT * from gif WHERE id = :id")
    suspend fun getGifs(id: String): PersistedGif

    @Query("SELECT * from gif ORDER BY title asc")
    suspend fun getAllGifs(): List<PersistedGif>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: PersistedGif)

}