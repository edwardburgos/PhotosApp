package com.example.photosapp.repository

import com.example.photosapp.entities.Photo
import kotlinx.coroutines.Deferred

interface MainRepository {
    fun getPhotosFromApi(): Deferred<List<Photo>>
    fun getPhotosFromDatabase(): List<Photo>
    fun insertPhotos(photos: List<Photo>)
}