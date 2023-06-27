package id.rrdev.tmdb_viper.feature.genres

import android.app.Activity
import android.content.Intent
import id.rrdev.tmdb_viper.feature.movie.MovieActivity
import java.io.Serializable

class GenreRouter(
    var activity: Activity?
) : GenreContract.Router {

    override fun goToDetailActivity(genres: Genre) {
        Intent(activity, MovieActivity::class.java).apply {
            putExtra(GENRE, genres as Serializable)
            activity?.startActivity(this)
        }
    }

    companion object {
        const val GENRE = "GENRE"
    }
}