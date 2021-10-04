package com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel

import androidx.room.Entity

//table for all users using pagination
@Entity(tableName = "users_by_pageNumber_table")
data class UsersByPageNumberData(
    val id : String,
    val firstName: String,
    val lastName: String,
    val emailAddress: String,
    val userName: String,
    val password: String,
    val phoneNumber: String,
    val gender: String,
    val age: String
)
