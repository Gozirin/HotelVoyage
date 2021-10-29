package com.example.hbapplicationgroupa.adapter.pastbookings_adapter

import android.app.Activity
import androidx.paging.PagingSource
import androidx.room.Index
import com.example.hbapplicationgroupa.MainActivity
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.database.dao.BookingByUserIdDao
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseItems
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseModel
import com.example.hbapplicationgroupa.network.CustomerModuleApiInterface
import javax.inject.Inject

/**
 * This is the paging data source for booking history data pagination
 */
class PastBookingPagingDataSource @Inject constructor(
    private val customerApi: CustomerModuleApiInterface
): PagingSource<Int, BookingByUserIdResponseItems>() {

    private val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookingByUserIdResponseItems> {
        //TODO
        return try {
            val nextPage = params.key ?: 1
            val response = customerApi.getCustomerBookingsByUserId(5, nextPage, authToken)
            return LoadResult.Page(
                data = response.body()!!.data.pageItems,
                prevKey = null,
                nextKey = response.body()?.data?.pageItems?.size?.plus(1)
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}