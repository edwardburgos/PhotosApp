package com.example.photosapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.photosapp.databinding.PhotoItemBinding
import com.example.photosapp.entities.Photo

class PhotosAdapter(val onClickListener: OnClickListener): ListAdapter<Photo, PhotosAdapter.PhotosPropertyViewHolder>(DiffCallback) {

    class PhotosPropertyViewHolder(private val binding: PhotoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bin(photo: Photo) {
            binding.photo = photo
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotosPropertyViewHolder {
        return PhotosPropertyViewHolder(PhotoItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotosPropertyViewHolder, position: Int) {
        val photoProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(photoProperty)
        }
        holder.bin(photoProperty)
    }

    class OnClickListener(val clickListener: (photo: Photo) -> Unit) {
        fun onClick(photo: Photo) = clickListener(photo)
    }
}