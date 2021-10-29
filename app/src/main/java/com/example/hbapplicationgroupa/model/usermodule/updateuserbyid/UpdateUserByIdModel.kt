package com.example.hbapplicationgroupa.model.usermodule.updateuserbyid

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "userById")

data class UpdateUserByIdModel(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("address")
    val address: String,
    @SerializedName("creditCard")
    val creditCard: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("updateAt")
    val updatedAt: String
)

