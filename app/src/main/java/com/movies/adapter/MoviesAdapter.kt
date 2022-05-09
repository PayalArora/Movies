package com.news.headlines.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.repository.utils.getProgressDrawable
import com.movies.databinding.ItemMoviesBinding
import com.movies.response.Search


class MoviesAdapter(val onItemClick: OnItemClickPosition) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    var response: List<Search>? = null

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val infalter = LayoutInflater.from(viewGroup.context)
        return ViewHolder(ItemMoviesBinding.inflate(infalter, viewGroup, false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.apply {
            imageView.setOnClickListener {
                Log.e("ONCLICK","ONCLICK")
                onItemClick.OnClick(position)
            }
            val item = response?.get(position)
            val circularProgressDrawable = viewHolder.binding.root.context.getProgressDrawable()
            circularProgressDrawable.start()

            txtMovies.text = item?.Title
            txtYear.text = item?.Year

            if (item?.Poster != null) {
                Glide.with(this.root).load(item?.Poster)
                    .placeholder(circularProgressDrawable).into(imageView)
            } else
                Glide.with(this.root).load(circularProgressDrawable)
                    .into(imageView)


        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = response?.size ?: 0

}

interface OnItemClickPosition {
    fun OnClick(position:Int)
}

