package com.example.ntconsultchallengeandroid.screens.home.ui

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.ntconsultchallengeandroid.R

class ImageLoader {

    fun loaderImage(context: Context, imageData: String?, holderImage: ImageView) {
        Glide.with(context).load(imageData)
            .placeholder(R.drawable.placeholder)
            .into(holderImage)
    }
}