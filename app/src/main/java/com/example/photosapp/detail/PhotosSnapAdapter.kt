package com.example.photosapp.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.photosapp.databinding.PhotoExtendedItemBinding
import com.example.photosapp.entities.Photo
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PhotosSnapAdapter(): ListAdapter<Photo, PhotosExtendedViewHolder>(DiffCallback), KoinComponent {
  //  class HomeRecyclerViewAdapter : RecyclerView.Adapter<HomeRecyclerViewHolder>() {



  //  var itemClickListener: ((view: View, item: Photo, position: Int) -> Unit)? = null

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
    ): PhotosExtendedViewHolder {
        val viewHolder: PhotosExtendedViewHolder by inject {
            parametersOf(PhotoExtendedItemBinding.inflate(LayoutInflater.from(parent.context)))
        }
        return viewHolder


    }

    override fun onBindViewHolder(holder: PhotosExtendedViewHolder, position: Int) {
//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(photoProperty)
//        }
//       // holder.itemClickListener = itemClickListener
//        when (holder) {
//            is ViewHolders.PhotoViewHolder -> holder.bin(getItem(position) as Photo)
//            is ViewHolders.PhotoOptionalViewHolder -> holder.bin(getItem(position) as Photo)
//
//        }

        val item = getItem(position)
//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(item)
//        }
        holder.bin(item)

       // val photoProperty = getItem(position)
//        if (position % 2 == 0) {
//            holder.
//        } else {
//            holder.itemClickListener = itemClickListener
//            when (holder) {
//                is HomeRecyclerViewHolder.DirectorViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Director)
//                is HomeRecyclerViewHolder.MovieViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Movie)
//                is HomeRecyclerViewHolder.TitleViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Title)
//            }
//        }
//        holder.Phot
//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(photoProperty)
//        }
//        if ()
//        holder.bin(photoProperty)
    }

//    class OnClickListener(val clickListener: (photo: Photo) -> Unit) {
//        fun onClick(photo: Photo) = clickListener(photo)
//    }

//    override fun getItemViewType(position: Int): Int {
//        return if (position % 2 == 0) R.layout.photo_item else R.layout.photo_item_optional
//
//    }
}