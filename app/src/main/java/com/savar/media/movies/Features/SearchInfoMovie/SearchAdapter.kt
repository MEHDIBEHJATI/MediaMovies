package ir.airport.myapplication.Features.SearchInfoMovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import ir.airport.myapplication.Pojo.Result
import ir.airport.myapplication.R
import ir.airport.myapplication.Utils.Consts
import kotlinx.android.synthetic.main.movie_item.view.*

class SearchAdapter (private val list: List<ir.airport.myapplication.Pojo.Result>, private val listener: (Long) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.MovieSearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieSearchViewHolder(v,listener)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        holder.bind(list[position])
    }


    class MovieSearchViewHolder(itemView: View, private val listener: (Long) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Result) {
            itemView.Title.text = movie.title
            itemView.Release.text = movie.release_date
            itemView.rate.text = "Rate:"+movie.vote_average.toString()
            itemView.Detail.text = movie.overview
            Picasso.get().load(Consts.SMALL_IMG + movie.backdrop_path).into(itemView.Poster)


            itemView.setOnClickListener {
                listener(movie.id)
            }
        }

    }
}
