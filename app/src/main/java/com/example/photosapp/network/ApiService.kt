package com.example.photosapp.network

import com.example.photosapp.entities.Album
import com.example.photosapp.entities.Photo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface ApiService {
    @GET("photos")
    fun getPhotos(@Query("_start") startAt: Int, @Query("_limit") endAT: Int):
            Deferred<List<Photo>>

    @GET("albums")
    fun getAlbums():
            Deferred<List<Album>>
}

object Api {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}