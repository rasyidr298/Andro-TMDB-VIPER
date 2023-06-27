package id.rrdev.tmdb_viper.feature.detail

import androidx.recyclerview.widget.RecyclerView
import id.rrdev.tmdb_viper.databinding.ItemReviewBinding


class ReviewViewHolder(
    private val binding: ItemReviewBinding,
): RecyclerView.ViewHolder(binding.root) {

    fun bind(
        data: MovieReview
    ) {
        with(binding) {
            tvAuthor.text = data.author
            tvContent.text = data.content
        }
    }
}