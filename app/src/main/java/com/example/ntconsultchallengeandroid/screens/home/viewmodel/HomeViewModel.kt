package com.example.ntconsultchallengeandroid.screens.home.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.ntconsultchallengeandroid.model.EventsListItem
import com.example.ntconsultchallengeandroid.screens.home.services.ServicesHome
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent

interface ViewModelHome {
    fun getEventsList()
}


class HomeViewModel(private val services: ServicesHome) : ViewModel(), KoinComponent,
    ViewModelHome {

    private var eventList = mutableListOf<EventsListItem>()

    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }


    override fun onCleared() {
        composite.clear()
        super.onCleared()
    }


    @SuppressLint("CheckResult")
    override fun getEventsList() {

        services.getEventsList().subscribe { response ->
            response.let { responseList ->
                responseList?.map { responseItem ->
                    eventList.add(responseItem)
                    eventList
                }
            }
        }

    }

}