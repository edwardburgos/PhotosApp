package com.example.photosapp

import android.view.View
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.photosapp.entities.Photo
import com.example.photosapp.overview.ApiStatus
import com.example.photosapp.overview.PhotosAdapter
import com.bumptech.glide.load.model.LazyHeaders

import com.bumptech.glide.load.model.GlideUrl




@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Photo>?) {
    val adapter = recyclerView.adapter as PhotosAdapter
    adapter.submitList(data)
}

//@BindingAdapter("listComments")
//fun bindCommentsRecyclerView(recyclerView: RecyclerView, data: List<Comment>?) {
//    val adapter = recyclerView.adapter as CommentsAdapter
//    adapter.submitList(data)
//}

// Use Glide to display the image
@BindingAdapter("imageUrl") // To use this * binding annotations *, 'kotlin-kapt' plugin is needed
fun bindImage(imgView: ImageView, url: String?) {
    url?.let {
        val url = GlideUrl(
            it, LazyHeaders.Builder()
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(imgView.context))
                .build()
        )
       // val imgUrl = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(url)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_baseline_broken_image_24))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgView)

//        Picasso.get()
//            .load(it)
//            .placeholder(R.drawable.loading_animation)
//            .error(R.drawable.ic_baseline_broken_image_24)
//            .into(imgView)
    }
}


@BindingAdapter("ApiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
            statusImageView.getLayoutParams().height = 600;
            statusImageView.getLayoutParams().width = 600;
            statusImageView.requestLayout();
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_bx_wifi_off)
            statusImageView.getLayoutParams().height = 200;
            statusImageView.getLayoutParams().width = 200;
            statusImageView.requestLayout();
        }
        else -> {
            statusImageView.visibility = View.GONE
        }
    }
}
//
//@BindingAdapter("ApiStatusCommentVisibility")
//fun bindStatusCommentVisibility(commentsLabel: TextView, status: ApiStatus?) {
//    when (status) {
//        ApiStatus.DONE -> {
//            commentsLabel.visibility = View.VISIBLE
//            commentsLabel.requestLayout()
//        }
//        else -> {
//            commentsLabel.visibility = View.GONE
//        }
//    }
//}