package id.rrdev.tmdb_viper.feature.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.rrdev.tmdb_viper.databinding.ItemMovieBinding

class MovieAdapter(
    private val onItemClicked: MovieContract.Presenter.MovieClickListener,
    ) : PagingDataAdapter<Any, RecyclerView.ViewHolder>(ITEM_CALLBACK_OBJ) {

    companion object {
        val ITEM_CALLBACK_OBJ = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return if (oldItem is Movie && newItem is Movie) {
                    oldItem.movieId == newItem.movieId
                } else {
                    false
                }
            }

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return if (oldItem is Movie && newItem is Movie) {
                    oldItem == newItem
                } else {
                    false
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val menuLayout = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(
            binding = menuLayout
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val menuItemHolder = holder as MovieViewHolder
        menuItemHolder.bind(
            onItemClicked = onItemClicked,
            data = getItem(position) as Movie
        )
    }

    class MovieViewHolder(
        private val binding: ItemMovieBinding,
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(
            onItemClicked: MovieContract.Presenter.MovieClickListener,
            data: Movie
        ) {
            with(binding) {
                //binding
            }
            binding.root.setOnClickListener {
                onItemClicked.onMovieClick(data)
            }
        }
    }
}