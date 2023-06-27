package id.rrdev.tmdb_viper.feature.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import id.rrdev.tmdb_viper.feature.genres.MovieDataSource

class MovieInteractor : MovieContract.Interactor {

    override fun fetchMovies(
        idGenre: Int,
        interactorOutPut: MovieContract.InteractorOutput
    ) {
        interactorOutPut.onSuccess(
            result = Pager(
                config = PagingConfig(1),
                pagingSourceFactory = {
                    MovieDataSource(idGenre)
                }
            )
        )
    }
}