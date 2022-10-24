package com.example.ntconsultchallengeandroid.screens.home.repository

import com.example.ntconsultchallengeandroid.model.EventsListItem
import com.example.ntconsultchallengeandroid.model.EventsListResponseModel
import com.example.ntconsultchallengeandroid.repositories.network.api.request.ApiRequest
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

interface RepositoryHome {
    fun getEventsList(): Observable<Response<EventsListResponseModel>>
    fun getEventsItem(): Observable<Response<EventsListItem>>
}

class HomeRepository : KoinComponent, RepositoryHome {

    private val apiCall by inject<ApiRequest>()

    override fun getEventsList(): Observable<Response<EventsListResponseModel>> {
        return apiCall.getApiEventsList()
    }

    override fun getEventsItem(): Observable<Response<EventsListItem>> {
        TODO("Not yet implemented")
    }


}