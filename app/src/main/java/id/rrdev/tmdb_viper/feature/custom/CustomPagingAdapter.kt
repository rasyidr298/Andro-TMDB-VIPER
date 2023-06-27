package id.rrdev.tmdb_viper.feature.custom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.rrdev.tmdb_viper.databinding.ItemMovieBinding
import id.rrdev.tmdb_viper.databinding.ItemReviewBinding
import id.rrdev.tmdb_viper.feature.detail.MovieReview
import id.rrdev.tmdb_viper.feature.detail.ReviewViewHolder
import id.rrdev.tmdb_viper.feature.movie.Movie
import id.rrdev.tmdb_viper.feature.movie.MovieContract
import id.rrdev.tmdb_viper.feature.movie.MovieViewHolder

class CustomPagingAdapter(
    private val onItemClicked: MovieContract.Presenter.MovieClickListener? = null,
) : PagingDataAdapter<Any, RecyclerView.ViewHolder>(ITEM_CALLBACK_OBJ) {

    companion object {
        val ITEM_CALLBACK_OBJ = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return if (oldItem is Movie && newItem is Movie) {
                    oldItem.movieId == newItem.movieId
                } else if (oldItem is MovieReview && newItem is MovieReview) {
                    oldItem.id == newItem.id
                }else {
                    false
                }
            }

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return if (oldItem is Movie && newItem is Movie) {
                    oldItem == newItem
                } else if (oldItem is MovieReview && newItem is MovieReview) {
                    oldItem == newItem
                } else {
                    false
                }
            }
        }

        const val MOVIE = 1
        const val REVIEW = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Movie -> {
                MOVIE
            }
            is MovieReview -> {
                REVIEW
            }
            else -> MOVIE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            MOVIE -> {
                val menuLayout = ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                MovieViewHolder(
                    binding = menuLayout
                )
            }

            REVIEW -> {
                val menuLayout = ItemReviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ReviewViewHolder(
                    binding = menuLayout
                )
            }

            else -> {
                val menuLayout = ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                MovieViewHolder(
                    binding = menuLayout
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MOVIE -> {
                val menuItemHolder = holder as MovieViewHolder
                onItemClicked?.let {
                    menuItemHolder.bind(
                        onItemClicked = it,
                        data = getItem(position) as Movie
                    )
                }
            }

            REVIEW -> {
                val menuItemHolder = holder as ReviewViewHolder
                menuItemHolder.bind(getItem(position) as MovieReview)
            }
        }
    }
}