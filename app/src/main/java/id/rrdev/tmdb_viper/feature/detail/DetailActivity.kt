package id.rrdev.tmdb_viper.feature.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.rrdev.tmdb_viper.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate( layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}