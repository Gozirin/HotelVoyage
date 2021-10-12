package com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid

data class GetHotelByIdResponseItemData(
    val id: String,
    val name: String,
    val description: String,
    val email: String,
    val address: String,
    val city: String,
    val rating: String,
    val gallery: ArrayList<String>,
    val reviews: ArrayList<GetHotelByIdResponseItemReviews>,
    val roomTypes: ArrayList<GetHotelByIdResponseItemRoomTypes>
)
