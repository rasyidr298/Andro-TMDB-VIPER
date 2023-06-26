package id.rrdev.tmdb_viper.feature.movie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieEntities (
    @SerializedName("results") val movies : List<Movie>
) : Serializable

data class Movie (
    @SerializedName("poster_path") val image : String?,
    @SerializedName("adult") val adult : Boolean,
    @SerializedName("overview") val overview : String,
    @SerializedName("release_date") val releaseDate : String,
    @SerializedName("title") val title : String,
    @SerializedName("popularity") val popularity : Number,
    @SerializedName("vote_average") val voteAverage : Number,
    @SerializedName("id") val movieId : Int
) : Serializable