package id.rrdev.tmdb_viper.utilities

import android.graphics.Color
import java.util.Random

fun randomColor(): Int {
    val rnd = Random()
    return Color.argb(
        255,
        rnd.nextInt(256),
        rnd.nextInt(256),
        rnd.nextInt(256)
    )
}