package id.rrdev.tmdb_viper.feature.movie

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.recyclerview.widget.GridLayoutManager
import id.rrdev.tmdb_viper.databinding.ActivityMovieBinding
import id.rrdev.tmdb_viper.feature.genres.Genre
import id.rrdev.tmdb_viper.feature.genres.GenreRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieActivity : AppCompatActivity(), MovieContract.View {

    private val binding by lazy { ActivityMovieBinding.inflate( layoutInflater) }
    private var presenter : MoviePresenter? = null
    private var genre: Genre? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = MoviePresenter(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            genre = intent.extras?.getParcelable(GenreRouter.GENRE, Genre::class.java)
        }

        genre?.id?.let {
            presenter?.onActivityCreated(
                idGenre = it
            )
        }

        binding.rvMovies.layoutManager = GridLayoutManager(
            this,
            2
        )
    }

    override fun setupRecyclerView(
        adapter: MovieAdapter,
        result: Pager<Int, Movie>
    ) {
        binding.rvMovies.adapter = adapter
        binding.rvMovies.post { startPostponedEnterTransition() }

        lifecycleScope.launch(Dispatchers.Main) {
            result.flow.cachedIn(this).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}