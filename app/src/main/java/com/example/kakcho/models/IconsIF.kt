package com.example.kakcho.models

import androidx.recyclerview.widget.DiffUtil

data class IconsIF(
    val published_at: String,
    val raster_sizes: List<RasterImage>,
    val styles: List<IfIconStyle>
){
    class DiffUtilCallBack : DiffUtil.ItemCallback<IconsIF>() {
        override fun areItemsTheSame(oldItem: IconsIF, newItem: IconsIF): Boolean {
            return oldItem.published_at == newItem.published_at
        }

        override fun areContentsTheSame(oldItem: IconsIF, newItem: IconsIF): Boolean {
            return oldItem == newItem
        }
    }

}