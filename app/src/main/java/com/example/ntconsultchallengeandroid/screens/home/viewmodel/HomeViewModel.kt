package com.example.ntconsultchallengeandroid.screens.home.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ntconsultchallengeandroid.model.EventsListItem
import com.example.ntconsultchallengeandroid.screens.home.services.ServicesHome
import com.example.ntconsultchallengeandroid.screens.home.ui.State
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent

interface ViewModelHome {
    fun getEventsList()
    val mutableEventList: MutableLiveData<List<EventsListItem>>
    val mutableEventListState: LiveData<State<Unit>>
}


class HomeViewModel(private val services: ServicesHome) : ViewModel(), KoinComponent,
    ViewModelHome {

    private var eventList = mutableListOf<EventsListItem>()


    private val _mutableEventListState = MutableLiveData<State<Unit>>()
    override val mutableEventListState: LiveData<State<Unit>> = _mutableEventListState


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
        _mutableEventListState.postValue(State.Loading())
        services.getEventsList().subscribe ({ response ->
            response.let { responseList ->
                responseList?.map { responseItem ->
                    eventList.add(responseItem)

                }
            }
            mutableEventList.postValue(eventList)
            _mutableEventListState.postValue(State.Success(Unit))
        }, { error  ->
            _mutableEventListState.postValue(State.Error(error))
        })
    }

}