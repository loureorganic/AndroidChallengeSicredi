package com.example.ntconsultchallengeandroid.di.modules

import com.example.ntconsultchallengeandroid.screens.home.ui.adapters.ImageLoader
import org.koin.dsl.module

var glideModule = module {

    single {
        val imageLoader = ImageLoader()
        imageLoader
    }
}