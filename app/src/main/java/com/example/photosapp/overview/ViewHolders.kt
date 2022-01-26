package com.example.photosapp.overview

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.photosapp.databinding.PhotoItemBinding
import com.example.photosapp.databinding.PhotoItemOptionalBinding
import com.example.photosapp.entities.Photo

// Para crear un viewHolder debemos extender de RecyclerView.ViewHolder(binding.root)
// Cada viewHolder estanderá de HomeRecyclerViewHolder
//
//class PhotosPropertyViewHolder(private val binding: PhotoItemBinding): RecyclerView.ViewHolder(binding.root) {
//    fun bin(photo: Photo) {
//        binding.photo = photo
//        binding.executePendingBindings()
//    }
//}

sealed class ViewHolders(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    //var itemClickListener: ((view: View, item: Photo, position: Int) -> Unit)? = null

    class PhotoViewHolder(private val binding: PhotoItemBinding) : ViewHolders(binding){
        fun bin(photo: Photo){
            binding.photo = photo
            binding.executePendingBindings()
        }
    }

    class PhotoOptionalViewHolder(private val binding: PhotoItemOptionalBinding) : ViewHolders(binding){
        fun bin(photo: Photo){
            binding.photo = photo
            binding.executePendingBindings()
        }
    }
}