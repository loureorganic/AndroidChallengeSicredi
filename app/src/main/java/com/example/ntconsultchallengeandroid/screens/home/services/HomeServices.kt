package com.example.ntconsultchallengeandroid.screens.home.services

import com.example.ntconsultchallengeandroid.model.EventsListResponseModel
import com.example.ntconsultchallengeandroid.screens.home.repository.RepositoryHome
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.component.KoinComponent


interface ServicesHome {
    fun getEventsList(): Observable<EventsListResponseModel?>
}

class HomeServices(private val repository: RepositoryHome) : ServicesHome, KoinComponent {

    override fun getEventsList(): Observable<EventsListResponseModel?> {
        return repository.getEventsList().filter { it.isSuccessful }.map { it.body() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}