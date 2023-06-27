package id.rrdev.tmdb_viper.feature.detail

import androidx.paging.Pager
import id.rrdev.tmdb_viper.feature.custom.CustomPagingAdapter
import id.rrdev.tmdb_viper.feature.movie.Movie

interface DetailContract {

    interface View {
        fun displayMovieImage(image: String)
        fun displayMovieName(title: String)
        fun displayMovieRating(rating: Float)
        fun displayMovieDescription(description: String)
        fun setupRecyclerView(
            adapter: CustomPagingAdapter,
            result: Pager<Int, Any>,
        )
    }

    interface Interactor {
        fun fetchReview(
            movieId: Int, interactorOutput: InteractorOutPut
        )
    }

    interface InteractorOutPut {
        fun onSuccess(
            result : Pager<Int, Any>
        )
        fun onFailure(message : String)
    }

    interface Presenter {
        fun getIntentMovie(movie : Movie?)
        fun onDestroy()
        fun onActivityCreated()
    }
}