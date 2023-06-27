package id.rrdev.tmdb_viper.feature.detail

import androidx.paging.Pager
import androidx.paging.PagingConfig
import id.rrdev.tmdb_viper.feature.custom.ContentDataSource
import id.rrdev.tmdb_viper.feature.custom.ContentEnum

class DetailInteractor : DetailContract.Interactor {

    override fun fetchReview(
        movieId: Int,
        interactorOutput: DetailContract.InteractorOutPut
    ) {
        interactorOutput.onSuccess(
            result = Pager(
                config = PagingConfig(1),
                pagingSourceFactory = {
                    ContentDataSource(
                        idMovie = movieId,
                        contentEnum = ContentEnum.Review
                    )
                }
            )
        )
    }
}