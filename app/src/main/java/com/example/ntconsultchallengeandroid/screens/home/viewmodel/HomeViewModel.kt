package com.example.ntconsultchallengeandroid.screens.home.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ntconsultchallengeandroid.model.EventsListItem
import com.example.ntconsultchallengeandroid.screens.home.services.ServicesHome
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent

interface ViewModelHome {
    fun getEventsList()
    val mutableEventList: MutableLiveData<List<EventsListItem>>
}


class HomeViewModel(private val services: ServicesHome) : ViewModel(), KoinComponent,
    ViewModelHome {

    private var eventList = mutableListOf<EventsListItem>()

    private val _mutableEventList = MutableLiveData<List<EventsListItem>>()
    override val mutableEventList: MutableLiveData<List<EventsListItem>> = _mutableEventList

    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }


    override fun onCleared() {
        composite.clear()
        super.onCleared()
    }


    @SuppressLint("CheckResult")
    override fun getEventsList() {
        services.getEventsList().subscribe ({ response ->
            response.let { responseList ->
                responseList?.map { responseItem ->
                    eventList.add(responseItem)
                }
            }
            mutableEventList.postValue(eventList)
        }, { error  ->

        })
    }

}