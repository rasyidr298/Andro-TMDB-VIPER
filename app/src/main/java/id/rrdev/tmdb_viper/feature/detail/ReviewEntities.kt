package id.rrdev.tmdb_viper.feature.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieReviewEntities (
    @SerializedName("results") val reviews : List<MovieReview>,
    @SerializedName("total_pages") val totalPages : Int
) : Serializable

data class MovieReview (
    @SerializedName("id") val id : String?,
    @SerializedName("author") val author : String,
    @SerializedName("content") val content : String,
    @SerializedName("avatar_path") val avatarPath : String
) : Serializable