package ir.airport.myapplication.Retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.airport.myapplication.Features.DetailsMovie.MovieDetailedVM
import ir.airport.myapplication.Features.SearchInfoMovie.MovieInfoVM
import javax.inject.Inject

class VMFactoryProvider @Inject constructor(val model: MovieModel) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieInfoVM::class.java) -> return MovieInfoVM(model) as T
            modelClass.isAssignableFrom(MovieDetailedVM::class.java) -> return MovieDetailedVM(model) as T

            else -> throw IllegalArgumentException("ViewModel Is not provided") as Throwable
        }
    }
}