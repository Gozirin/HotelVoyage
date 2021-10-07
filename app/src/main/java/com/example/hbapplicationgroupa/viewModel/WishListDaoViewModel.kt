package com.example.hbapplicationgroupa.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel.CustomerWishListData
import com.example.hbapplicationgroupa.repository.wishListDaoRepository.WishListDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class WishListDaoViewModel ( val wishListDaoRepository: WishListDaoRepository): ViewModel(){
     val customerWishListLiveData: MutableLiveData<List<CustomerWishListData>> = MutableLiveData()
}