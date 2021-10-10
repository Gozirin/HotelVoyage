package com.example.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid

import androidx.room.Entity

@Entity(tableName = "updateUserPhotoByUserId")
data class UpdateUserPhotoByUserIdResponseItem(
    val url: String
)