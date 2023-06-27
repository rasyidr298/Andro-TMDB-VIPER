package id.rrdev.tmdb_viper.utilities

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.load(url: String) {
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .thumbnail(0.25f)
        .into(this)
}