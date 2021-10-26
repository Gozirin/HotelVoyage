package com.example.hbapplicationgroupa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hbapplicationgroupa.adapter.pastbookings_adapter.PastBookingPagingDataSource
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseItems
import com.example.hbapplicationgroupa.network.CustomerModuleApiInterface
import com.example.hbapplicationgroupa.repository.customermodulerepository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val customerRepository: CustomerRepository, private val api: CustomerModuleApiInterface): ViewModel() {

    //booking history flow data using pagination from PastBookingPagingDataSource
    val bookingHistory: Flow<PagingData<BookingByUserIdResponseItems>> = Pager(PagingConfig(pageSize = 5)) {
        PastBookingPagingDataSource(api)
    }.flow
        .cachedIn(viewModelScope)
}