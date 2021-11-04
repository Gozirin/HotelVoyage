package com.example.hbapplicationgroupa.model.authmodule.refreshToken


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("newJwtAccessToken")
    val newJwtAccessToken: String?,
    @SerializedName("newRefreshToken")
    val newRefreshToken: String?
)