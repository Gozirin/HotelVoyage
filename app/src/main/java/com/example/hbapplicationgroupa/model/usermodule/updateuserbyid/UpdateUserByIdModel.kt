package com.example.hbapplicationgroupa.model.usermodule.updateuserbyid

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userById")
data class UpdateUserByIdModel(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val FirstName: String,
    val LastName: String,
    val EmailAddress: String,
    val UserName: String,
    val Password: String,
    val PhoneNumber: String,
    val Gender: String,
    val Age: Int
)
