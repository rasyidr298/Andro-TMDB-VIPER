package id.rrdev.tmdb_viper.feature.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.rrdev.tmdb_viper.databinding.ActivityMovieBinding
import id.rrdev.tmdb_viper.feature.custom.CustomPagingAdapter
import id.rrdev.tmdb_viper.feature.genres.Genre
import id.rrdev.tmdb_viper.feature.genres.GenreRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieActivity : AppCompatActivity(), MovieContract.View {

    private val binding by lazy { ActivityMovieBinding.inflate( layoutInflater) }
    private var presenter : MoviePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.let {
            it.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed()}
            it.rvMovies.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }

        presenter = MoviePresenter(this)
        presenter.let {
            if (it != null) {
                it.getGenreData(
                    genre = intent.getSerializableExtra(GenreRouter.GENRE) as Genre
                )
                it.onActivityCreated()
            }
        }
    }

    override fun setupRecyclerView(
        adapter: CustomPagingAdapter,
        result: Pager<Int, Any>
    ) {
        binding.let {
            it.rvMovies.adapter = adapter
            it.rvMovies.post { startPostponedEnterTransition() }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            result.flow.cachedIn(this).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}