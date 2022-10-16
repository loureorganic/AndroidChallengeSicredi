package com.example.ntconsultchallengeandroid.di

import android.app.Application
import com.example.ntconsultchallengeandroid.di.modules.apiModule
import com.example.ntconsultchallengeandroid.di.modules.glideModule
import com.example.ntconsultchallengeandroid.di.modules.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            androidFileProperties()

            modules(
                listOf(
                    apiModule,
                    glideModule,
                    homeModule
                )
            )

        }
    }
}
