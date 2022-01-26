package com.example.photosapp.database

import com.example.photosapp.entities.Photo
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(photos: List<Photo>)

    @Query("SELECT * FROM photo_table ORDER BY id DESC")
    fun getAllPhotos(): List<Photo>
}
