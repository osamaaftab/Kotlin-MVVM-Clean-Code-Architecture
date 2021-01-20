package com.osamaaftab.dindinn.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter("url")
fun ImageView.setImageUrl(url: String) {
    this.load(url)
}