package id.rrdev.tmdb_viper.feature.detail

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.rrdev.tmdb_viper.R
import id.rrdev.tmdb_viper.databinding.ActivityDetailBinding
import id.rrdev.tmdb_viper.feature.movie.Movie
import id.rrdev.tmdb_viper.feature.movie.MovieRouter
import id.rrdev.tmdb_viper.utilities.Constants
import id.rrdev.tmdb_viper.utilities.load

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate( layoutInflater) }
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            movie = intent.extras?.getParcelable(MovieRouter.MOVIE, Movie::class.java)
        }

        setupView()
    }

    private fun setupView() {
        binding.let {
            it.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed()}
            it.tvTitle.text = movie?.title
            it.tvDesc.text = movie?.overview
            it.imageView.load(Constants.MEDIUM_SIZE+movie?.image)
        }
    }
}