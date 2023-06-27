package id.rrdev.tmdb_viper.feature.genres

import id.rrdev.tmdb_viper.helpers.MovieNetworkHelper
import id.rrdev.tmdb_viper.utilities.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreInteractor : GenreContract.Interactor {

    override fun fetchGenres(
        interactorOutPut: GenreContract.InteractorOutput
    ) {
        MovieNetworkHelper.service
            .getGenres(Constants.API_KEY)
            .enqueue(object : Callback<GenreEntities> {
                override fun onResponse(
                    call: Call<GenreEntities>,
                    response: Response<GenreEntities>
                ) {
                    response.body()?.genres?.let {
                        interactorOutPut.onSuccess(it)
                    }
                }
                override fun onFailure(call: Call<GenreEntities>, t: Throwable) {
                    interactorOutPut.onFailure(t.toString())
                }
            })
    }

}