package com.example.ntconsultchallengeandroid.di.modules

import com.example.ntconsultchallengeandroid.repositories.network.api.instance.RetrofitHelper
import com.example.ntconsultchallengeandroid.repositories.network.api.request.ApiRequest
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    val baseUrl = "https://5f5a8f24d44d640016169133.mockapi.io/"

    factory {
        val retrofitHelper = RetrofitHelper()
        retrofitHelper.initRetrofit(baseUrl)
    }

    factory<ApiRequest> {
        val retrofit: Retrofit = get()
        retrofit.create(ApiRequest::class.java)
    }
}