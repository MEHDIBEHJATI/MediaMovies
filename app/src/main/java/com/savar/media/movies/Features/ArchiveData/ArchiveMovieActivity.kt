package ir.airport.myapplication.Features.ArchiveData

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.airport.myapplication.DB.MovieClass
import ir.airport.myapplication.DB.MovieDataBase
import ir.airport.myapplication.R
import kotlinx.android.synthetic.main.activity_archive_movie.*
import java.lang.ref.WeakReference
import java.util.ArrayList


class ArchiveMovieActivity : AppCompatActivity() {

    private var db: MovieDataBase?=null
    private lateinit var movieClass: MutableList<MovieClass>
    private lateinit var adapter:MovieArchiveAdapter
    private lateinit var recycleview:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive_movie)
           recyclerArchive.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, true)
          // val movie = intent.getParcelableExtra<>()LExtra("movie")
       // movieClass=movie
       // recyclerArchive.adapter=MovieArchiveAdapter(movieClass)
        //initializeVies()
       // displayList();

    }


    private fun displayList() {
        db = MovieDataBase.getInstance(this@ArchiveMovieActivity)
        RetrieveTask(this).execute()
    }

    private class RetrieveTask internal constructor(context: ArchiveMovieActivity?) : AsyncTask<Void?, Void?, List<MovieClass?>?>() {
        private val activityReference: WeakReference<ArchiveMovieActivity?>
        override fun doInBackground(vararg p0: Void?): List<MovieClass?>? {
            return if (activityReference.get() != null) activityReference.get()!!.db!!.movieDao!!.getMovieList() else null
        }

        override fun onPostExecute(movieClass: List<MovieClass?>?) {
            if (movieClass != null && movieClass.size > 0) {
                //activityReference.get()!!.movieClass.clear()
               //activityReference.get()!!.movieClass.addAll(movieClass)
                // hides empty text view

                activityReference.get()!!.adapter!!.notifyDataSetChanged()
            }
        }

        // only retain a weak reference to the activity
        init {
            activityReference = WeakReference(context)
        }


    }

    private fun initializeVies() {
        recycleview = findViewById<RecyclerView>(R.id.recyclerArchive)
        recycleview.layoutManager = LinearLayoutManager(this)
        movieClass = ArrayList()
       // adapter = MovieArchiveAdapter(this)
       // recycleview.setAdapter(adapter)
    }
    override fun onDestroy() {

        super.onDestroy()
    }


}


