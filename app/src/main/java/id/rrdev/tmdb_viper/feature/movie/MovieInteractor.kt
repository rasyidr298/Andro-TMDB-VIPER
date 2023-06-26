package id.rrdev.tmdb_viper.feature.movie

import id.rrdev.tmdb_viper.helpers.MovieNetworkHelper
import id.rrdev.tmdb_viper.utilities.enum.CallSelector
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieInteractor : MovieContract.Interactor {

    override fun fetchMovies(
        idGenre: Int,
        page: Int,
        interactorOutPut: MovieContract.InteractorOutput,
    ) {
        MovieNetworkHelper.service
            .getMoviesByGenre(API_KEY, idGenre, page)
            .enqueue(object : Callback<MovieEntities> {
                override fun onResponse(
                    call: Call<MovieEntities>,
                    response: Response<MovieEntities>
                ) {
                    response.body()?.movies?.let {
                        interactorOutPut.onSuccess(it, CallSelector.MOVIES)
                    }
                }

                override fun onFailure(call: Call<MovieEntities>, t: Throwable) {
                    interactorOutPut.onFailure(t.toString())
                }
            })
    }

    companion object {
        const val API_KEY = "07d84b920daba19e3cd63fda9637a04d"
    }

}