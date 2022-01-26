package com.example.photosapp.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.photosapp.database.PhotosDatabase
import com.example.photosapp.entities.Photo
import com.example.photosapp.network.Api
import com.example.photosapp.overview.ApiStatus
import kotlinx.coroutines.*

class MainRepository(
    application: Application
) {

    private val photoDao = PhotosDatabase.getInstance(application).photoDao

//    fun getComments(
//        post: Post,
//        viewModelScope: CoroutineScope,
//        _status: MutableLiveData<ApiStatus>,
//        _comments: MutableLiveData<List<Comment>>,
//        _selectedPost: MutableLiveData<PostWithComments>
//    ) {
//        viewModelScope.launch {
//            lateinit var getCommentsDeferred: Deferred<List<Comment>>
//            withContext(Dispatchers.IO) {
//                getCommentsDeferred =
//                    Api.retrofitService.getComments(post.id.toString())
//            }
//            try {
//                _status.value = ApiStatus.LOADING
//                lateinit var listResult: List<Comment>
//                withContext(Dispatchers.IO) {
//                    listResult = getCommentsDeferred.await()
//                }
//                _status.value = ApiStatus.DONE
//                if (listResult.size > 0) {
//                    _comments.value = listResult
//                    withContext(Dispatchers.IO) {
//                        commentDao.insertAll(listResult)
//                    }
//                }
//                val commentsRetrieved = _comments.value
//                val comments: List<Comment> =
//                    if (commentsRetrieved != null) commentsRetrieved else listOf<Comment>()
//                _selectedPost.value = PostWithComments(
//                    post,
//                    comments.sortedBy { it.id }.reversed()
//                )
//            } catch (t: Throwable) {
//                lateinit var postComments: List<Comment>
//                withContext(Dispatchers.IO) {
//                    postComments = commentDao.getPostComments(post.id)
//                }
//                if (t.message == "Unable to resolve host \"jsonplaceholder.typicode.com\": No address associated with hostname") {
//                    _selectedPost.value = PostWithComments(
//                        post,
//                        postComments
//                    )
//                    _status.value = if (postComments.size != 0) ApiStatus.DONE else ApiStatus.DONEWITHOUTCOMMENTS
//                } else {
//                    _status.value = ApiStatus.ERROR
//                }
//            }
//
//        }
//    }


    fun getPhotosFromApi() = Api.retrofitService.getPhotos(0, 100)
    fun getPhotosFromDatabase() = photoDao.getAllPhotos()
    fun insertPhotos(photos: List<Photo>) = photoDao.insertAll(photos)

}