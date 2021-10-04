package com.example.hbapplicationgroupa.models.adminModule.userByIdModel

import androidx.room.Entity

//table for a user with a particular id
@Entity(tableName = "user_by_id_table")
data class UserByIdData(
    val firstName: String,
    val lastName: String,
    val emailAddress: String,
    val userName: String,
    val password: String,
    val phoneNumber: String,
    val gender: String,
    val age: String
)
