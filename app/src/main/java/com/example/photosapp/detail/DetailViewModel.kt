package com.example.photosapp.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.photosapp.entities.Photo
import com.example.photosapp.overview.ApiStatus
import com.example.photosapp.repository.MainRepository
import kotlinx.coroutines.*

class DetailViewModel(app: Application, val mainRepository: MainRepository) :
    AndroidViewModel(app) {

    private val _status = MutableLiveData<ApiStatus>()
    val status: MutableLiveData<ApiStatus>
        get() = _status

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            lateinit var getPropertiesDeferred: Deferred<List<Photo>>
            withContext(Dispatchers.IO) {
                getPropertiesDeferred = async { mainRepository.getPhotosFromDatabase() }
            }
            try {
                _status.value = ApiStatus.LOADING
                lateinit var listResult: List<Photo>
                withContext(Dispatchers.IO) {
                    listResult = getPropertiesDeferred.await()
                }
                _status.value = ApiStatus.DONE
                if (listResult.size > 0) {
                    _photos.value = listResult.sortedBy { it.id }.reversed()
                }
            } catch (t: Throwable) {
                _status.value = ApiStatus.ERROR

            }
        }
    }
}