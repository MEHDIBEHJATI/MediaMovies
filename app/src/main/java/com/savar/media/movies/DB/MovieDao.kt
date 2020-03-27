package ir.airport.myapplication.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dagger.Provides
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg movieClass: MovieClass)


    @Query("SELECT DISTINCT * FROM Movie GROUP BY Title")
    fun getMovieList(): List<MovieClass>

    @Query("SELECT * FROM Movie WHERE MovId=MovId")
    fun getaSpecialMovie(): MovieClass

    @Query("DELETE FROM Movie")
    fun deleteAll()



}