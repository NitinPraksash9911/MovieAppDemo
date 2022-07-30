package com.example.movie_demo.utils

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movie_demo.R
import com.google.android.material.snackbar.Snackbar


fun String.snack(color: Int, view: View, duration: Int = Snackbar.LENGTH_LONG) {
    val snackbar = Snackbar.make(view, this, duration)
    snackbar.view.setBackgroundColor(color)
    snackbar.setTextColor(Color.WHITE)
    snackbar.show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadImageWithUrl(
    imgUrl: String?,
    @DrawableRes defaultImageId: Int = R.drawable.ic_broken_image
) {
    Glide.with(this.context)
        .load(imgUrl ?: defaultImageId)
        .centerCrop()
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(defaultImageId)
        )
        .into(this)
}
