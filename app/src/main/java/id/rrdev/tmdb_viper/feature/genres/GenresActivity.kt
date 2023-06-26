package id.rrdev.tmdb_viper.feature.genres

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import id.rrdev.tmdb_viper.databinding.ActivityGenresBinding

class GenresActivity : AppCompatActivity(), GenreContract.View {

    private val binding by lazy { ActivityGenresBinding.inflate(layoutInflater) }
    private var presenter : GenrePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = GenrePresenter(this)
        presenter?.onActivityCreated()
        binding.rvGenre.layoutManager = GridLayoutManager(this, 2)
    }

    override fun setupRecyclerView(adapter: GenreAdapter) {
        binding.rvGenre.adapter = adapter
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}