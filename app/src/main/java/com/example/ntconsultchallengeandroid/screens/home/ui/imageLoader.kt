package com.example.ntconsultchallengeandroid.screens.home.ui

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoader {

    fun loaderImage(context: Context, imageData: String?, holderImage: ImageView) {
        Glide.with(context).load(imageData).into(holderImage)
    }
}