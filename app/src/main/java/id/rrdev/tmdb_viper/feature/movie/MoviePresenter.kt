package id.rrdev.tmdb_viper.feature.movie

import android.app.Activity
import android.util.Log
import id.rrdev.tmdb_viper.utilities.enum.CallSelector

class MoviePresenter(
    private var view: MovieContract.View?
    ) : MovieContract.Presenter, MovieContract.InteractorOutput {

    private var interactor: MovieContract.Interactor? = MovieInteractor()
    private var router: MovieContract.Router? = MovieRouter(view as? Activity)

    override fun onActivityCreated() {
//        interactor?.fetchMovies(this)
    }

    override fun onSuccess(
        result: List<Movie>,
        selector: CallSelector
    ) {
        view?.setupRecyclerView(
            selector,
            MovieAdapter(object : MovieContract.Presenter.MovieClickListener {
                override fun onMovieClick(result: Movie) {
//                    router?.goToDetailActivity(result)
                }
            })
        )
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