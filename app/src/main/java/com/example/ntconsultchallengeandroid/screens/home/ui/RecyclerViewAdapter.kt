package com.example.ntconsultchallengeandroid.screens.home.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ntconsultchallengeandroid.R
import com.example.ntconsultchallengeandroid.model.EventsListItem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecyclerViewAdapter(var context: Context, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(),
    KoinComponent {

    private val glide by inject<ImageLoader>()
    private var dataList = emptyList<EventsListItem>()

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
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {


        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.cardItemTitle)
        var description: TextView = itemView.findViewById(R.id.cardItemDescription)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}