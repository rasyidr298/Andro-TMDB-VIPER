package id.rrdev.tmdb_viper.feature.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.rrdev.tmdb_viper.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMovieBinding.inflate( layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}