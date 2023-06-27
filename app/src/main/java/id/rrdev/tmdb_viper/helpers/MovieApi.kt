package id.rrdev.tmdb_viper.helpers

import id.rrdev.tmdb_viper.feature.detail.MovieReviewEntities
import id.rrdev.tmdb_viper.feature.genres.GenreEntities
import id.rrdev.tmdb_viper.feature.movie.MovieEntities
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String
    ): Call<GenreEntities>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") idGenre: Int,
        @Query("page") page: Int = 1,
    ): MovieEntities

    @GET("movie/{idMovie}/reviews")
    suspend fun getReview(
        @Path("idMovie") idMovie: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): MovieReviewEntities
}