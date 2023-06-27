package id.rrdev.tmdb_viper.helpers

import id.rrdev.tmdb_viper.utilities.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieNetworkHelper {
    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    val service: MovieApi by lazy { retrofit().create(MovieApi::class.java) }
}