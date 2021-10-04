package com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel

data class CustomerWishList(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<CustomerWishListData>,
    val Message: String,
    val errors: String?
)
