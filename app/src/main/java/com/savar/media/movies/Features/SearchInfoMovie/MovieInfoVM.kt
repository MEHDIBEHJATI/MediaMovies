package ir.airport.myapplication.Features.SearchInfoMovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.airport.myapplication.Pojo.MovieResponceModel
import ir.airport.myapplication.Retrofit.MovieModel
import javax.inject.Inject

class MovieInfoVM (private val model: MovieModel): ViewModel()  {
    val DataInfo = MutableLiveData<MovieResponceModel>()
    private val disposable = CompositeDisposable()



    fun setInfo(apiKey: String, query: String){
        disposable.add(model.setSearchofMovie(apiKey,query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                    DataInfo.value=it

            },{

                Log.d("Error",it.message)

            }))
    }
    fun getLiveInfo(): LiveData<MovieResponceModel> = DataInfo

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}