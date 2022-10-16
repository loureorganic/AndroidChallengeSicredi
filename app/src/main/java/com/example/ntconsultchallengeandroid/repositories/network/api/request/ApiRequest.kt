package com.example.ntconsultchallengeandroid.repositories.network.api.request

import com.example.ntconsultchallengeandroid.model.EventsListResponseModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ApiRequest {

    @GET("api/events")
    fun getApiEventsList() : Observable<Response<EventsListResponseModel>>

}