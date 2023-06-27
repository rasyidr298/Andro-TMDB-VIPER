package id.rrdev.tmdb_viper.feature.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.rrdev.tmdb_viper.databinding.ItemMovieBinding
import id.rrdev.tmdb_viper.utilities.Constants
import id.rrdev.tmdb_viper.utilities.load

class MovieAdapter(
    private val onItemClicked: MovieContract.Presenter.MovieClickListener,
) : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(ITEM_CALLBACK_OBJ) {

    companion object {
        val ITEM_CALLBACK_OBJ = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
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
                tvName.text = data.title
                imgBackground.load(Constants.MEDIUM_SIZE+data.image)
            }
            binding.root.setOnClickListener {
                onItemClicked.onMovieClick(data)
            }
        }
    }
}