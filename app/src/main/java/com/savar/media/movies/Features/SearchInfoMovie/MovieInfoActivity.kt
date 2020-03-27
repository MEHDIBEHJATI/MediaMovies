package ir.airport.myapplication.Features.SearchInfoMovie

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import ir.airport.myapplication.Features.ArchiveData.ArchiveMovieActivity
import ir.airport.myapplication.Features.DetailsMovie.MovieDetailActivity
import ir.airport.myapplication.Pojo.Result
import ir.airport.myapplication.R
import ir.airport.myapplication.Retrofit.DaggerFactoryComponent
import ir.airport.myapplication.Retrofit.VMFactoryProvider
import ir.airport.myapplication.Utils.Consts
import ir.airport.myapplication.Utils.Consts.Companion.MOVIE_ID
import kotlinx.android.synthetic.main.activity_movie_info.*

class MovieInfoActivity : AppCompatActivity() {
    lateinit var vm: MovieInfoVM
    lateinit var factory: VMFactoryProvider

    private val disposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)

        val factory = DaggerFactoryComponent.create().provideVMFactory()
        vm = ViewModelProvider(this, factory).get(MovieInfoVM::class.java)

        vm.getLiveInfo().observe(this, Observer {
            progressBar.visibility = View.INVISIBLE
            showRecyclerList(it.results)
        })

        imgSrch.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            vm.setInfo(Consts.api_key, edtSearch.text.toString())
        }
       // imgArch.setOnClickListener {
       //     val intent = Intent(this, ArchiveMovieActivity::class.java)
        //    startActivity(intent)

       // }
    }

    private fun showRecyclerList(results: List<Result>) {
        recycler.adapter = SearchAdapter(results) { showDetailsPage(it) }
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun showDetailsPage(movieId: Long) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_ID,movieId)
        startActivity(intent)
    }
}
