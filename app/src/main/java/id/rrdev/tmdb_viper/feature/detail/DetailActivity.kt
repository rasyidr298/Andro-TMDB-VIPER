package id.rrdev.tmdb_viper.feature.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import id.rrdev.tmdb_viper.databinding.ActivityDetailBinding
import id.rrdev.tmdb_viper.feature.custom.CustomPagingAdapter
import id.rrdev.tmdb_viper.feature.movie.Movie
import id.rrdev.tmdb_viper.feature.movie.MovieRouter
import id.rrdev.tmdb_viper.utilities.load
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity(), DetailContract.View {

    private val binding by lazy { ActivityDetailBinding.inflate( layoutInflater) }
    private var presenter : DetailPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.let {
            it.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed()}
            it.rvReview.layoutManager = LinearLayoutManager(this)
        }

        presenter = DetailPresenter(this)
        presenter.let {
            if (it != null) {
                it.getIntentMovie(
                    movie = intent.getSerializableExtra(MovieRouter.MOVIE) as Movie
                )
                it.onActivityCreated()
            }
        }
    }

    override fun displayMovieImage(image: String) {
        binding.imageView.load(image)
    }

    override fun displayMovieName(title: String) {
        binding.tvTitle.text = title
    }

    override fun displayMovieRating(rating: Float) {
        binding.rating.rating = rating/2
    }

    override fun displayMovieDescription(description: String) {
        binding.tvDesc.text = description
    }

    override fun setupRecyclerView(
        adapter: CustomPagingAdapter,
        result: Pager<Int, Any>
    ) {
        binding.let {
            it.rvReview.adapter = adapter
            it.rvReview.post { startPostponedEnterTransition() }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            result.flow.cachedIn(this).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}