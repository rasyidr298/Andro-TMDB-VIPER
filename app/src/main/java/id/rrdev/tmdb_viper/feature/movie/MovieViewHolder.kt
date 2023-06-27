package id.rrdev.tmdb_viper.feature.movie

import androidx.recyclerview.widget.RecyclerView
import id.rrdev.tmdb_viper.databinding.ItemMovieBinding
import id.rrdev.tmdb_viper.utilities.Constants
import id.rrdev.tmdb_viper.utilities.load


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