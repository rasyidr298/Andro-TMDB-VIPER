package id.rrdev.tmdb_viper.feature.genres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rrdev.tmdb_viper.databinding.ItemGenreBinding
import id.rrdev.tmdb_viper.utilities.randomColor

class GenreAdapter(
    private val genres: List<Genre>,
    private val onItemClicked: GenreContract.Presenter.MovieClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val menuLayout = ItemGenreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GenreViewHolder(binding = menuLayout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val menuItemHolder = holder as GenreViewHolder
        menuItemHolder.bind(onItemClicked, genres[position])
    }

    override fun getItemCount(): Int = genres.size

    class GenreViewHolder(
        private val binding: ItemGenreBinding,
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(
            onItemClicked: GenreContract.Presenter.MovieClickListener,
            data: Genre,
        ) {

            with(binding) {
                tvName.text = data.name
                root.setBackgroundColor(randomColor())
            }
            binding.root.setOnClickListener {
                onItemClicked.onMovieClick(data)
            }
        }
    }
}
