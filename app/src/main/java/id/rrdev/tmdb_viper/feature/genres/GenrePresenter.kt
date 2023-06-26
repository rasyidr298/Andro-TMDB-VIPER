package id.rrdev.tmdb_viper.feature.genres

import android.app.Activity
import android.util.Log
import id.rrdev.tmdb_viper.utilities.enum.CallSelector

class GenrePresenter(
    private var view: GenreContract.View?
    ) : GenreContract.Presenter, GenreContract.InteractorOutput {

    private var interactor: GenreContract.Interactor? = GenreInteractor()
    private var router: GenreContract.Router? = GenreRouter(view as? Activity)

    override fun onActivityCreated() {
        interactor?.fetchGenres(this)
    }

    override fun onSuccess(
        genres: List<Genre>
    ) {
        view?.setupRecyclerView(
            GenreAdapter(genres, object : GenreContract.Presenter.MovieClickListener {
                override fun onMovieClick(genres: Genre) {
                    router?.goToDetailActivity(genres)
                }
            })
        )
    }

    override fun onFailure(message: String) {
        Log.e("onFailure", message)
    }

    override fun onDestroy() {
        view = null
        interactor = null
        router = null
    }
}