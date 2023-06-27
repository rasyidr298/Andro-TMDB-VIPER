package id.rrdev.tmdb_viper.feature.genres

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.rrdev.tmdb_viper.feature.movie.Movie
import id.rrdev.tmdb_viper.feature.movie.MovieEntities
import id.rrdev.tmdb_viper.helpers.MovieNetworkHelper
import id.rrdev.tmdb_viper.utilities.Constants


class MovieDataSource(
    private val idGenre: Int,
): PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Movie> {

        var resultMovieEntities: MovieEntities? = null
        val resultMovie = mutableListOf<Movie>()
        val nextPageNumber = params.key ?: 1

        return try {

            val response = MovieNetworkHelper.service
                .getMoviesByGenre(Constants.API_KEY, idGenre, nextPageNumber)

            resultMovieEntities = response
            resultMovie.addAll(response.movies)

            LoadResult.Page(
                data = resultMovie,
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < resultMovieEntities.totalPages) nextPageNumber + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}