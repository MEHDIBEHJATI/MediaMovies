package ir.airport.myapplication.Features.DetailsMovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.airport.myapplication.Pojo.MovieDetailedResponceModel
import ir.airport.myapplication.Pojo.MovieResponceModel
import ir.airport.myapplication.Retrofit.MovieModel
import javax.inject.Inject

class MovieDetailedVM (val model: MovieModel): ViewModel(){
    private val disposable = CompositeDisposable()
    val DetailsData = MutableLiveData<MovieDetailedResponceModel>()

    fun setDetailes(movieId: Long , apiKey: String){
        disposable.add(model.setDetailsMovie(movieId,apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                DetailsData.value=it

            },{

                Log.d("Error",it.message)

            }))
    }
    fun getLiveInfo(): LiveData<MovieDetailedResponceModel> = DetailsData

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}