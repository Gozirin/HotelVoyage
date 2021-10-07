package com.example.hbapplicationgroupa.repository.wishListDaoRepository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.database.dao.WishListDao
import com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel.CustomerWishListData
import javax.inject.Inject

class WishListDaoRepository(val wishListDao: WishListDao):
    WishListDaoRepositoryInterface {
    override fun getCustomerWishlists(): LiveData<List<CustomerWishListData>> {
        return wishListDao.getCustomerWishlists()
    }

    override fun insertWishlist(customerWishListData: CustomerWishListData) {
        wishListDao.insertWishlist(customerWishListData)
    }

    override fun removeWishlist(customerWishListData: CustomerWishListData) {
        wishListDao.removeWishlist(customerWishListData)
    }
}