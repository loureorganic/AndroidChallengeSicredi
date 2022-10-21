package com.example.ntconsultchallengeandroid.screens.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ntconsultchallengeandroid.databinding.ActivityHomeBinding
import com.example.ntconsultchallengeandroid.model.EventsListItem
import com.example.ntconsultchallengeandroid.screens.home.viewmodel.ViewModelHome
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener{

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

        binding.homeRecyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        binding.homeRecyclerView.adapter = recyclerViewAdapter
        getList()
    }

    override fun onStart() {
        super.onStart()

    }

    private fun getList() {
        homeViewModel.getEventsList()
        homeViewModel.mutableEventList.observe(this) { result ->
            recyclerViewAdapter.setDataList(result)
            mutableEventList.postValue(result)
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()

    }

}