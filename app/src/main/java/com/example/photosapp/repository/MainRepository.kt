package com.example.photosapp.repository

import com.example.photosapp.database.PhotoDao
import com.example.photosapp.entities.Photo
import com.example.photosapp.network.ApiService

class MainRepository(
    val photoDao: PhotoDao,
    val apiService: ApiService
) {

    fun getPhotosFromApi() = apiService.getPhotos(0, 100)
    fun getPhotosFromDatabase() = photoDao.getAllPhotos()
    fun insertPhotos(photos: List<Photo>) = photoDao.insertAll(photos)

}