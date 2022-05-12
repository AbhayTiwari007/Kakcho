package com.example.kakcho.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kakcho.R
import com.example.kakcho.models.IconsIF

class IconsAdapter :
    ListAdapter<IconsIF, IconsAdapter.StoryViewHolder>(IconsIF.DiffUtilCallBack()) {

    class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textView: TextView = view.findViewById(R.id.textView)

        fun setData(iconData: IconsIF){
            if(iconData.styles.isEmpty()){
                textView.text = "No Name"
            }else{
                textView.text = iconData.styles.first().name
            }
            if(iconData.raster_sizes.isNotEmpty()){
                imageView.load(iconData.raster_sizes.last().formats.first().preview_url)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(View.inflate( parent.context, R.layout.item_card, null))
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.setData(currentList[position])
    }


}