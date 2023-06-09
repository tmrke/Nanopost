package ru.ageev.nanopost.ui.images_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.ageev.nanopost.data.model.Image
import ru.ageev.nanopost.databinding.ItemImageBinding

val diffUtilCallback = object : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}

class ImagesAdapter : PagingDataAdapter<Image, ImagesViewHolder>(diffUtilCallback) {

    var onImageClick: (String) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding =
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImagesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        getItem(position)?.let { image ->
            holder.bind(image, onImageClick)
        }
    }
}