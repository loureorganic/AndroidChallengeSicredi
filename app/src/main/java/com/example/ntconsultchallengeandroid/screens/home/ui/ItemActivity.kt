package com.example.ntconsultchallengeandroid.screens.home.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ntconsultchallengeandroid.R
import com.example.ntconsultchallengeandroid.databinding.ActivityItemBinding
import com.example.ntconsultchallengeandroid.model.EventsListItem
import org.koin.android.ext.android.inject

class ItemActivity : AppCompatActivity() {

    private lateinit var value: EventsListItem
    private lateinit var binding: ActivityItemBinding
    private val glide by inject<ImageLoader>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        getInformationOfHome()
        setInformation()
        controlButtonCancel()
        sharePublication()
    }

    private fun controlButtonCancel() {
        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }

    private fun sharePublication() {
        binding.icShare.setOnClickListener {
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT, "Evento: $value.title")
                this.type = "text/plain"
            }
            startActivity(shareIntent)
        }
    }

    private fun setInformation() {
        binding.txtTitleItemMain.text = value.title
        binding.txtDescriptionItemMain.text = value.description
        binding.txtValue.text = getString(R.string.price_value, value.price.toString())
    }

    private fun getInformationOfHome() {
        val extras = intent.extras
        if (extras != null) {
            value = extras.getSerializable("EVENT_ITEM_INFORMATION") as EventsListItem
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}