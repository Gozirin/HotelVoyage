package com.example.hbapplicationgroupa.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserItem(
    @PrimaryKey(autoGenerate = true)
    val number: Int, //added this because of pagination which involves digits
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
