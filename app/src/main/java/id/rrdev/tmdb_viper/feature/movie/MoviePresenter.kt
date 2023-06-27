package id.rrdev.tmdb_viper.feature.movie

import android.app.Activity
import android.util.Log
import androidx.paging.Pager

class MoviePresenter(
    private var view: MovieContract.View?
) : MovieContract.Presenter, MovieContract.InteractorOutput {

    private var interactor: MovieContract.Interactor? = MovieInteractor()
    private var router: MovieContract.Router? = MovieRouter(view as? Activity)

    override fun onActivityCreated(
        idGenre: Int
    ) {
        interactor?.fetchMovies(
            idGenre = idGenre,
            interactorOutPut = this
        )
    }

    override fun onSuccess(result: Pager<Int, Movie>) {
        val adapter = MovieAdapter(object : MovieContract.Presenter.MovieClickListener {
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
}