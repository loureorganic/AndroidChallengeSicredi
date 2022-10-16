package com.example.ntconsultchallengeandroid.screens.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ntconsultchallengeandroid.databinding.ActivityHomeBinding
import com.example.ntconsultchallengeandroid.screens.home.viewmodel.ViewModelHome
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel by inject<ViewModelHome>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()


        homeViewModel.getEventsList()

    }

}