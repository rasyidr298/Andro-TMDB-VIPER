package id.rrdev.tmdb_viper.feature.movie

import android.app.Activity
import android.content.Intent
import id.rrdev.tmdb_viper.feature.detail.DetailActivity
import id.rrdev.tmdb_viper.utilities.Constants
import java.io.Serializable

class MovieRouter(
    var activity: Activity?
) : MovieContract.Router {

    override fun goToDetailActivity(results: Movie) {
        Intent(activity, DetailActivity::class.java).apply {
            putExtra(MOVIE, results as Serializable)
            activity?.startActivity(this)
        }
    }

    companion object {
        const val MOVIE = "MOVIE"
    }
}