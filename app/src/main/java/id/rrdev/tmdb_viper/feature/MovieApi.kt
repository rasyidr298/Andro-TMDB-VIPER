package id.rrdev.tmdb_viper.feature

import id.rrdev.tmdb_viper.feature.genres.GenreEntities
import id.rrdev.tmdb_viper.feature.movie.MovieEntities
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(GENRES)
    fun getGenres(
        @Query("api_key") apiKey: String
    ): Call<GenreEntities>

    @GET(MOVIES)
    fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") idGenre: Int,
        @Query("page") page: Int = 1,
    ): Call<MovieEntities>

    @GET("movie/{idMovie}/videos")
    fun getReview(
        @Query("api_key") apiKey: String,
        @Path("idMovie") idMovie: Int,
        @Query("page") page: Int = 1,
    ): Call<MovieEntities>

    companion object {
        const val GENRES = "genre/movie/list"
        const val MOVIES = "discover/movie"
    }
}