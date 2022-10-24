package com.example.ntconsultchallengeandroid.screens.home.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ntconsultchallengeandroid.R
import com.example.ntconsultchallengeandroid.model.EventsListItem
import com.google.android.material.button.MaterialButton
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecyclerViewAdapter(var context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(),
    KoinComponent {

    private val glide by inject<ImageLoader>()
    private var dataList = emptyList<EventsListItem>()

    var onItemClick:  ((EventsListItem) -> Unit)? = null

    internal fun setDataList(userImageList: List<EventsListItem>) {
        this.dataList = userImageList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.title.text = data.title
        holder.description.text = data.description

        glide.loaderImage(context = context, imageData = data.image, holderImage = holder.image)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(data)
        }
        holder.buttonSeeMore.setOnClickListener {
            onItemClick?.invoke(data)
        }


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.cardItemTitle)
        var description: TextView = itemView.findViewById(R.id.cardItemDescription)
        var buttonSeeMore : MaterialButton = itemView.findViewById(R.id.btnSeeMore)


    }

}