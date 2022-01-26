package com.example.photosapp.network

import com.example.photosapp.entities.Photo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    fun getPhotos(@Query("_start") startAt: Int, @Query("_limit") endAT: Int):
            Deferred<List<Photo>>
}