package id.rrdev.tmdb_viper.feature.movie

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


interface MovieContract {
    interface View{
        fun setupRecyclerView(
            adapter: MovieAdapter,
            result: Pager<Int, Movie>,
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
            result : Pager<Int, Movie>
        )
        fun onFailure(message : String)
    }
    interface Presenter{
        fun onActivityCreated(
            idGenre: Int
        )
        fun onDestroy()
        interface MovieClickListener {
            fun onMovieClick(result: Movie)
        }
    }
    interface Router{
        fun goToDetailActivity(results: Movie)
    }
}