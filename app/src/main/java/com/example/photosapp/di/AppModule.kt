package com.example.photosapp.di

import com.example.photosapp.database.PhotosDatabase
import com.example.photosapp.databinding.PhotoExtendedItemBinding
import com.example.photosapp.databinding.PhotoItemBinding
import com.example.photosapp.databinding.PhotoItemOptionalBinding
import com.example.photosapp.detail.DetailViewModel
import com.example.photosapp.detail.PhotosExtendedViewHolder
import com.example.photosapp.entities.Photo
import com.example.photosapp.network.ApiService
import com.example.photosapp.overview.OverviewViewModel
import com.example.photosapp.overview.ViewHolders
import com.example.photosapp.repository.MainRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single { MainRepository(get(), get()) }
    factory { (binding: PhotoExtendedItemBinding) -> PhotosExtendedViewHolder(binding) }
    factory { (binding: PhotoItemBinding) -> ViewHolders.PhotoViewHolder(binding) }
    factory { (binding: PhotoItemOptionalBinding) -> ViewHolders.PhotoOptionalViewHolder(binding) }
    viewModel { OverviewViewModel(androidApplication(), get())}
    viewModel { (photo : Photo) -> DetailViewModel(androidApplication(), get()) }
}