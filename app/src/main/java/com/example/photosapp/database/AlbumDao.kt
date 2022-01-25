package com.example.photosapp.database

import com.example.photosapp.entities.Photo
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photosapp.entities.Album

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: List<Album>)

    @Query("SELECT * FROM album_table")
    fun getAllAlbums(): LiveData<List<Album>>
}