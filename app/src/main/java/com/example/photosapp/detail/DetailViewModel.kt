package com.example.photosapp.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.photosapp.entities.Photo

class DetailViewModel(photo: Photo, app: Application) : AndroidViewModel(app) {

    private val _selectedPhoto = MutableLiveData<Photo>()
    val selectedPhoto: LiveData<Photo>
        get() = _selectedPhoto

    init {
        _selectedPhoto.value = photo
    }
}