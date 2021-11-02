package com.example.hbapplicationgroupa.model.usermodule.getuserbyid

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "getUserById")
data class GetUserByIdResponseItem(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val age: Int,
    val id: String,
    val email: String,
    val phoneNumber: String,
    val userName: String,
    val creditCard: String,
    val address: String,
    val state: String
)
