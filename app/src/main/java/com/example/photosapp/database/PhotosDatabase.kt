package com.example.photosapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.photosapp.entities.Photo

@Database(entities = [Photo::class], version = 3, exportSchema = false)
abstract class PhotosDatabase : RoomDatabase() {

    abstract val photoDao: PhotoDao

    companion object {

        @Volatile
        private var INSTANCE: PhotosDatabase? = null

        fun getInstance(context: Context): PhotosDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PhotosDatabase::class.java,
                        "photos_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}