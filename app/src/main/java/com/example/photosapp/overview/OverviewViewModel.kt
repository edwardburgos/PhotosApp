package com.example.photosapp.overview

import android.app.Application
import androidx.lifecycle.*
import com.example.photosapp.entities.Photo
import com.example.photosapp.network.Api
import com.example.photosapp.repository.MainRepository
import kotlinx.coroutines.*

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
        viewModelScope.launch {
            lateinit var getPropertiesDeferred: Deferred<List<Photo>>
            withContext(Dispatchers.IO) {
                getPropertiesDeferred = mainRepository.getPhotosFromApi()
            }
            try {
                if (showLoading) _status.value = ApiStatus.LOADING
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
                    withContext(Dispatchers.IO) {
                        mainRepository.insertPhotos(listResult)

                    }
                }
            } catch (t: Throwable) {
                lateinit var databasePhotos: List<Photo>
                withContext(Dispatchers.IO) {
                    databasePhotos = mainRepository.getPhotosFromDatabase()
                }
                if (t.message == "Unable to resolve host \"jsonplaceholder.typicode.com\": No address associated with hostname" && databasePhotos.size != 0) {
                    _photos.value = databasePhotos
                    _status.value = ApiStatus.DONE
                } else {
                    _status.value = ApiStatus.ERROR
                }

            }
        }
    }

    fun displayPropertyDetails(photo: Photo) {
        _navigateToSelectedPhoto.value = photo
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedPhoto.value = null
    }
}
