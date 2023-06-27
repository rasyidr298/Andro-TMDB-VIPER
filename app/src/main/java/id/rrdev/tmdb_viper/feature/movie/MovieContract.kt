package id.rrdev.tmdb_viper.feature.movie

import androidx.paging.Pager
import id.rrdev.tmdb_viper.feature.custom.CustomPagingAdapter
import id.rrdev.tmdb_viper.feature.genres.Genre


interface MovieContract {
    interface View{
        fun setupRecyclerView(
            adapter: CustomPagingAdapter,
            result: Pager<Int, Any>,
        )
    }
    interface Interactor{
        fun fetchMovies(
            idGenre: Int,
            interactorOutPut: InteractorOutput
        )
    }
    interface InteractorOutput{
        fun onSuccess(
            result : Pager<Int, Any>
        )
        fun onFailure(message : String)
    }
    interface Presenter{
        fun onActivityCreated()
        fun onDestroy()
        interface MovieClickListener {
            fun onMovieClick(result: Movie)
        }
        fun getGenreData(genre: Genre?)
    }
    interface Router{
        fun goToDetailActivity(results: Movie)
    }
}