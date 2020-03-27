package ir.airport.myapplication.Features.DetailsMovie

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.airport.myapplication.DB.MovieClass
import ir.airport.myapplication.DB.MovieDao
import ir.airport.myapplication.DB.MovieDataBase
import ir.airport.myapplication.Features.ArchiveData.MovieArchiveAdapter
import ir.airport.myapplication.Pojo.MovieDetailedResponceModel
import ir.airport.myapplication.R
import ir.airport.myapplication.Retrofit.DaggerFactoryComponent
import ir.airport.myapplication.Utils.Consts
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {

    lateinit var vm: MovieDetailedVM
    //private val newMovieActivityRequestCode = 1
    //lateinit var movieVM: MovieVM
    private var db:MovieDataBase?=null
   private var moviedao:MovieDao?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        recyclerResult.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        val movieId = intent.getLongExtra(Consts.MOVIE_ID, -1L)

        check(movieId != -1L) { "You must pass the movie Id" }

        val factory = DaggerFactoryComponent.create().provideVMFactory()
        vm = ViewModelProvider(this, factory).get(MovieDetailedVM::class.java)
        vm.getLiveInfo().observe(this, Observer {
            showMovieDetails(it)
        })

        vm.setDetailes(movieId, Consts.api_key)
        movId.text = movieId.toString()
        //db = MovieDataBase.getInstance(this@MovieDetailActivity)
        btnSave.setOnClickListener {
            Observable.fromCallable(
                {
                    db= MovieDataBase.getInstance(this)
                    moviedao=db?.movieDao
                    val movieClass = MovieClass(
                        movId.toString(),
                        Title.text.toString(),
                        //postxt.text.toString(),
                        Rate.text.toString(),
                        Release.text.toString(),
                        Desc.text.toString()
                    )
                    with(moviedao){
                        this?.insert(movieClass)

                    }
                    db?.movieDao?.getMovieList()
                }

            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                   // val intent = Intent(this, MovieDetailActivity::class.java)
                   // intent.putExtra("movie",it?.contains(it))
                   // startActivity(intent)
                    Log.d("size: ",it?.size.toString())
                    recyclerResult.adapter=MovieArchiveAdapter(it!!,this)
                    //Log.d("size: ",it.toString())



                },{

                    Log.d("Error",it.message)

                })

            Toast.makeText(this@MovieDetailActivity,"Movie data Saved",Toast.LENGTH_LONG).show()
            //InsertTask(this@MovieDetailActivity, movieClass).execute()
            //Toast.makeText(this,"Movie Saved in Database",Toast.LENGTH_LONG).show()


        }

    }
    //private fun setResult(movieClass: MovieClass, flag: Int) {
     //   setResult(flag, Intent().putExtra("movie", movieClass))
     //   finish()
   // }

    private fun showMovieDetails(details: MovieDetailedResponceModel) {

        postxt.text=details.poster_path
        covtxt.text=details.backdrop_path
        Title.text = details.title
        Rate.text = "Rate:"+details.vote_average.toString()
        Release.text = getString(R.string.movie_release_date, details.release_date)
        Duration.text = getString(R.string.movie_length, details.runtime.toString())
        Desc.text = details.overview
        Picasso.get().load(Consts.BIG_IMG + details.backdrop_path).into(Cover)
        Picasso.get().load(Consts.SMALL_IMG + details.poster_path).into(Poster)

    }

   // private fun setResult(movieClass: MovieClass, flag: Int) {
   //     setResult(flag, Intent().putExtra("Movie", movieClass))
   //     finish()
  //  }
   //  private class InsertTask internal constructor(context: MovieDetailActivity, movieClass: MovieClass) :
     //       AsyncTask<Void?, Void?, Boolean>() {
       //     private val activityReference: WeakReference<MovieDetailActivity>
         //   private var movieClass:MovieClass? = null

     //    override fun doInBackground(vararg params: Void?): Boolean {
       //      activityReference.get()?.db?.movieDao?.insert(movieClass!!)
       //      return true

         //}


            // onPostExecute runs on main thread
           // override fun onPostExecute(bool: Boolean) {
               // if (bool) {
               //     activityReference.get()!!.setResult(movieClass, 1)
              //      activityReference.get()!!.finish()
              //  }
            //}

            // only retain a weak reference to the activity
            //init {
              //  activityReference = WeakReference(context)
                //this.movieClass = movieClass
            //}




     //}

    }






