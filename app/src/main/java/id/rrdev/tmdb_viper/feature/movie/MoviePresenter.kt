package id.rrdev.tmdb_viper.feature.movie

import android.app.Activity
import android.util.Log
import androidx.paging.Pager
import id.rrdev.tmdb_viper.feature.custom.CustomPagingAdapter
import id.rrdev.tmdb_viper.feature.genres.Genre

class MoviePresenter(
    private var view: MovieContract.View?
) : MovieContract.Presenter, MovieContract.InteractorOutput {

    private var interactor: MovieContract.Interactor? = MovieInteractor()
    private var router: MovieContract.Router? = MovieRouter(view as? Activity)
    private var genre : Genre? = null

    override fun onActivityCreated( ) {
        interactor?.fetchMovies(
            idGenre = genre?.id ?: 0,
            interactorOutPut = this
        )
    }

    override fun onSuccess(result: Pager<Int, Any>) {
        val adapter = CustomPagingAdapter(object : MovieContract.Presenter.MovieClickListener {
                override fun onMovieClick(result: Movie) {
                    router?.goToDetailActivity(result)
                }
            }
        )
        view?.setupRecyclerView(adapter, result)
    }

    override fun onFailure(message: String) {
        Log.d("onFailure", message)
    }

    override fun onDestroy() {
        view = null
        interactor = null
        router = null
    }

    override fun getGenreData(genre: Genre?) {
        this.genre = genre
    }
}