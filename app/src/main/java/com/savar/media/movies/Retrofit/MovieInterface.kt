package ir.airport.myapplication.Retrofit

import io.reactivex.Observable
import ir.airport.myapplication.Pojo.MovieDetailedResponceModel
import ir.airport.myapplication.Pojo.MovieResponceModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {
    @GET("search/movie")
    fun getInfoMovie(@Query("api_key")api_key: String,
                     @Query("query")query: String
    ): Observable<MovieResponceModel>

    @GET("movie/{movie_id}")
    fun getDetailsMovie(@Path("movie_id") movie_id: Long,
                        @Query("api_key") apiKey: String
    ): Observable<MovieDetailedResponceModel>
}