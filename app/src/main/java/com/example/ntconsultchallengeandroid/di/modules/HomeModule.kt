package com.example.ntconsultchallengeandroid.di.modules

import com.example.ntconsultchallengeandroid.screens.home.ui.RecyclerViewAdapter
import com.example.ntconsultchallengeandroid.screens.home.repository.HomeRepository
import com.example.ntconsultchallengeandroid.screens.home.repository.RepositoryHome
import com.example.ntconsultchallengeandroid.screens.home.services.HomeServices
import com.example.ntconsultchallengeandroid.screens.home.services.ServicesHome
import com.example.ntconsultchallengeandroid.screens.home.viewmodel.HomeViewModel
import com.example.ntconsultchallengeandroid.screens.home.viewmodel.ViewModelHome
import org.koin.dsl.module

val homeModule = module {

    single<RepositoryHome> {
        HomeRepository()
    }

    single<ServicesHome> {
        HomeServices(get())
    }

    single<ViewModelHome> {
        HomeViewModel(get())
    }

    single {
        RecyclerViewAdapter(get(), get())
    }
}