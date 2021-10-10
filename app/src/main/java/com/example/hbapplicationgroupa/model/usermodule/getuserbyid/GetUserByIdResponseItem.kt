package com.example.hbapplicationgroupa.model.usermodule.getuserbyid

import androidx.room.Entity

@Entity(tableName = "getUserById")
data class GetUserByIdResponseItem(
    val FirstName: String,
    val LastName: String,
    val EmailAddress: String,
    val UserName: String,
    val Password: String,
    val PhoneNumber: String,
    val Gender: String,
    val Age: Int
)
