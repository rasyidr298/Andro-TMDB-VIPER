package id.rrdev.tmdb_viper.feature.custom

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.rrdev.tmdb_viper.helpers.MovieNetworkHelper
import id.rrdev.tmdb_viper.utilities.Constants


class ContentDataSource(
    private val idGenre: Int = 0,
    private val idMovie: Int = 0,
    private val contentEnum: ContentEnum
): PagingSource<Int, Any>() {

    override fun getRefreshKey(state: PagingState<Int, Any>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Any> {

        val contentResult = mutableListOf<Any>()
        var totalPage = 1
        val nextPageNumber = params.key ?: 1

        return try {
            when(contentEnum) {

                ContentEnum.Movie -> {
                    val response = MovieNetworkHelper.service
                        .getMoviesByGenre(Constants.API_KEY, idGenre, nextPageNumber)

                    totalPage = response.totalPages
                    contentResult.addAll(response.movies)
                }

                ContentEnum.Review -> {
                    val response = MovieNetworkHelper.service
                            .getReview(idMovie, Constants.API_KEY, nextPageNumber)

                    totalPage = response.totalPages
                    contentResult.addAll(response.reviews)
                }
            }

            LoadResult.Page(
                data = contentResult,
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < totalPage) nextPageNumber + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

enum class ContentEnum {
    Movie, Review
}