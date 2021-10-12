package com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals

data class GetTopDealsResponseModel(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<GetTopDealsResponseItem>,
    val Message: String,
    val errors: String?
)
