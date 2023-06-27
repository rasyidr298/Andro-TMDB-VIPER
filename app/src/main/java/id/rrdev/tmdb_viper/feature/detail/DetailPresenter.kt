package id.rrdev.tmdb_viper.feature.detail

import android.util.Log
import androidx.paging.Pager
import id.rrdev.tmdb_viper.feature.custom.CustomPagingAdapter
import id.rrdev.tmdb_viper.feature.movie.Movie
import id.rrdev.tmdb_viper.utilities.Constants

class DetailPresenter(
    private var view: DetailContract.View?
) : DetailContract.Presenter, DetailContract.InteractorOutPut {

    private var interactor: DetailContract.Interactor? = DetailInteractor()
    private var movie: Movie? = null

    override fun onActivityCreated() {
        interactor?.fetchReview(
            movieId = movie?.movieId ?: 0,
            interactorOutput = this
        )
    }

    override fun onSuccess(result: Pager<Int, Any>) {
        val adapter = CustomPagingAdapter()
        view?.setupRecyclerView(adapter, result)
    }

    override fun onFailure(message: String) {
        Log.d("onFailure", message)
    }

    override fun onDestroy() {
        view = null
        interactor = null
    }

    override fun getIntentMovie(movie: Movie?) {
        this.movie = movie
        view?.displayMovieName(movie?.title ?: "")
        view?.displayMovieImage(Constants.MEDIUM_SIZE + movie?.image)
        view?.displayMovieDescription(movie?.overview ?: "")
        view?.displayMovieRating(movie?.voteAverage?.toFloat() ?: 0f)
    }
}