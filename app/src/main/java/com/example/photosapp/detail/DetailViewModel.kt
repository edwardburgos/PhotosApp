package com.example.photosapp.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.photosapp.entities.Photo
import com.example.photosapp.overview.ApiStatus
import com.example.photosapp.repository.MainRepository
import kotlinx.coroutines.*

class DetailViewModel(photo: Photo, app: Application) : AndroidViewModel(app) {

    private val mainRepository = MainRepository(app)

    private val _selectedPhoto = MutableLiveData<Photo>()
    val selectedPhoto: LiveData<Photo>
        get() = _selectedPhoto

    private val _status = MutableLiveData<ApiStatus>()
    val status: MutableLiveData<ApiStatus>
        get() = _status

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    init {
        //_selectedPhoto.value = photo
        println(photo.id)
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

//                    if(movies is Resource.Success && directors is Resource.Success){
//                        homeItemsList.add(HomeRecyclerViewItem.Title(1, "Recommended Movies"))
//                        homeItemsList.addAll(movies.value)
//                        homeItemsList.add(HomeRecyclerViewItem.Title(2, "Top Directors"))
//                        homeItemsList.addAll(directors.value)
//                        _homeListItemsLiveData.postValue(Resource.Success(homeItemsList))
//                    }else{
//                        withContext(Dispatchers.IO) {
//                            mainRepository.insertPhotos(listResult)
//
//                        }
                    }
                } catch (t: Throwable) {
//                    lateinit var databasePhotos: List<Photo>
//                    withContext(Dispatchers.IO) {
//                        databasePhotos = mainRepository.getPhotosFromDatabase()
//                    }
//                    if (t.message == "Unable to resolve host \"jsonplaceholder.typicode.com\": No address associated with hostname" && databasePhotos.size != 0) {
//                        _photos.value = databasePhotos
//                        _status.value = ApiStatus.DONE
//                    } else {
                        _status.value = ApiStatus.ERROR
                    //}

                }
            }

    }
}