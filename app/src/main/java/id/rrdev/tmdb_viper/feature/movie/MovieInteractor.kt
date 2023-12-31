package id.rrdev.tmdb_viper.feature.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import id.rrdev.tmdb_viper.feature.custom.ContentDataSource
import id.rrdev.tmdb_viper.feature.custom.ContentEnum

class MovieInteractor : MovieContract.Interactor {

    override fun fetchMovies(
        idGenre: Int,
        interactorOutPut: MovieContract.InteractorOutput
    ) {
        interactorOutPut.onSuccess(
            result = Pager(
                config = PagingConfig(1),
                pagingSourceFactory = {
                    ContentDataSource(
                        idGenre = idGenre,
                        contentEnum = ContentEnum.Movie
                    )
                }
            )
        )
    }
}