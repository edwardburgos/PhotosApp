package com.example.photosapp.repository

import com.example.photosapp.database.PhotoDao
import com.example.photosapp.entities.Photo
import com.example.photosapp.network.ApiService
import kotlinx.coroutines.Deferred

class MainRepositoryImpl(
    val photoDao: PhotoDao,
    val apiService: ApiService
): MainRepository {

    override fun getPhotosFromApi() = apiService.getPhotos(0, 100)
    override fun getPhotosFromDatabase() = photoDao.getAllPhotos()
    override fun insertPhotos(photos: List<Photo>) = photoDao.insertAll(photos)

}