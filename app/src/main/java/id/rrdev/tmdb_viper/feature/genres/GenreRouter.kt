package id.rrdev.tmdb_viper.feature.genres

import android.app.Activity
import android.content.Intent
import id.rrdev.tmdb_viper.feature.detail.DetailActivity
import id.rrdev.tmdb_viper.utilities.enum.MovieBase
import java.io.Serializable

class GenreRouter(
    var activity: Activity?
) : GenreContract.Router {

    override fun goToDetailActivity(genres: Genre) {
        Intent(activity, DetailActivity::class.java).apply {
            putExtra(MovieBase.INTENT_HOLDER, genres as Serializable)
            activity?.startActivity(this)
        }
    }
}