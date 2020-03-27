package ir.airport.myapplication.Features.ArchiveData

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import ir.airport.myapplication.DB.MovieClass
import ir.airport.myapplication.R
import ir.airport.myapplication.Utils.Consts
import kotlinx.android.synthetic.main.moviearch_item.view.*

class MovieArchiveAdapter internal constructor(val items:List<MovieClass>,val context: Context) : RecyclerView.Adapter<MovieArchiveAdapter.MovieArchiveViewHolder>()
{
    //private val inflater: LayoutInflater = LayoutInflater.from(context)
   // private var movieClass = emptyList<MovieClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieArchiveViewHolder {

        return MovieArchiveViewHolder(LayoutInflater.from(context).inflate(R.layout.moviearch_item,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: MovieArchiveViewHolder, position: Int) {

        val current = items.get(position)
        holder.title.text = current.Title
        holder.release.text = current.Release
        holder.rate.text = current.Rate
        holder.detail.text = current.Desc
        //Picasso.get().load(Consts.SMALL_IMG + current.postxt).into(holder.poster)

    }


     class MovieArchiveViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val title =view.TitleArch
        //val poster = view.PosterArch
        val release = view.ReleaseArch
        val rate = view.rateArch
        val detail = view.DetailArch


    }

//private fun RequestCreator.into(poster: TextView) {}


}








