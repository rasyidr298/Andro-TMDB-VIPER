package id.rrdev.tmdb_viper.helpers

import id.rrdev.tmdb_viper.feature.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieNetworkHelper {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val service: MovieApi by lazy { retrofit().create(MovieApi::class.java) }
}