package com.example.ntconsultchallengeandroid.screens.home.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ntconsultchallengeandroid.R
import com.example.ntconsultchallengeandroid.databinding.ActivityHomeBinding
import com.example.ntconsultchallengeandroid.model.EventsListItem
import com.example.ntconsultchallengeandroid.screens.home.ui.adapters.RecyclerViewAdapter
import com.example.ntconsultchallengeandroid.screens.home.viewmodel.ViewModelHome
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import retrofit2.HttpException

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel by inject<ViewModelHome>()

    private val recyclerViewAdapter by inject<RecyclerViewAdapter> {
        parametersOf(applicationContext, this)
    }


    private val _mutableEventList = MutableLiveData<List<EventsListItem>>()
    val mutableEventList: MutableLiveData<List<EventsListItem>> = _mutableEventList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
        getList()
        onClickItem()
    }

    private fun onClickItem() {
        recyclerViewAdapter.onItemClick = {
            val intent = Intent(this, ItemActivity::class.java)
            intent.putExtra("EVENT_ITEM_INFORMATION", it)
            startActivity(intent)
        }
    }

    private fun setRecyclerView() {
        binding.homeRecyclerView.setHasFixedSize(true)
        binding.homeRecyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        binding.homeRecyclerView.adapter = recyclerViewAdapter
    }

    private fun getList() {
        homeViewModel.getEventsList()
        homeViewModel.mutableEventListState.observe(this) { state ->
            when(state) {
                is State.Success -> {
                    homeViewModel.mutableEventList.observe(this) { result ->
                        recyclerViewAdapter.setDataList(result)
                        mutableEventList.postValue(result)
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }

                is State.Error -> {
                    val errorResponse = errorHandling.convertErrorBody(state.throwable as HttpException)
                    val errorResponseMessage = errorResponse?.error?.message.toString()
                    binding.errorText.text = getString(R.string.error_api, errorResponseMessage)
                }
            }

        }

    }

}