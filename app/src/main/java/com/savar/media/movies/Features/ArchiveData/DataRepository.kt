package ir.airport.myapplication.Features.ArchiveData

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.airport.myapplication.DB.MovieClass
import ir.airport.myapplication.DB.MovieDao
import javax.inject.Inject

class DataRepository (private val movieDao: MovieDao){

    val allMovies: List<MovieClass> = movieDao.getMovieList()

    suspend fun insert(movieClass: MovieClass) {
        movieDao.insert(movieClass)
    }
}