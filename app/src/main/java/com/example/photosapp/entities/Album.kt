package com.example.photosapp.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "album_table")
data class Album (
    @PrimaryKey
    var id: Int,

    @ColumnInfo
    var userId: Int,

    @ColumnInfo
    var title: String
): Parcelable