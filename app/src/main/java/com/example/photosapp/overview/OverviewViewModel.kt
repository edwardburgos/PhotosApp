package com.example.photosapp.overview

import android.app.Application
import androidx.lifecycle.*
import com.example.photosapp.entities.Photo
import com.example.photosapp.repository.MainRepository

enum class ApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel(
    app: Application
) : AndroidViewModel(app) {

    private val mainRepository = MainRepository(app)

    private val _status = MutableLiveData<ApiStatus>()
    val status: MutableLiveData<ApiStatus>
        get() = _status

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

//    private val _comments = MutableLiveData<List<Comment>>()
//    val comments: LiveData<List<Comment>>
//        get() = _comments

    private val _navigateToSelectedPhoto = MutableLiveData<Photo>()
    val navigateToSelectedPhoto: LiveData<Photo>
        get() = _navigateToSelectedPhoto

    init {
        getPhotos(true)
    }

    fun getPhotos(showLoading: Boolean) {
        mainRepository.getPhotos(showLoading, viewModelScope, _status, _photos)
    }

    fun displayPropertyDetails(photo: Photo) {
        _navigateToSelectedPhoto.value = photo
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedPhoto.value = null
    }
}
