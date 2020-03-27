package ir.airport.myapplication.Retrofit

import io.reactivex.Observable
import ir.airport.myapplication.Pojo.MovieDetailedResponceModel
import ir.airport.myapplication.Pojo.MovieResponceModel
import ir.airport.myapplication.Utils.Consts
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MovieModel @Inject constructor(){

    fun Movie(): MovieInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(Consts.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(MovieInterface::class.java)
    }


    fun setSearchofMovie(apiKey: String, query: String): Observable<MovieResponceModel>
    {
        return Movie().getInfoMovie(apiKey,query)
    }
    fun setDetailsMovie(movieId: Long, apiKey: String): Observable<MovieDetailedResponceModel> {
        return Movie().getDetailsMovie(movieId,apiKey)
    }

}