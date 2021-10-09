package com.example.hbapplicationgroupa.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel.CustomerWishListData
import com.example.hbapplicationgroupa.repository.wishListDaoRepository.WishListDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class WishListDaoViewModel
     @Inject constructor ( val wishListDaoRepository: WishListDaoRepository): ViewModel(){
     val customerWishListLiveData: MutableLiveData<List<CustomerWishListData>> = MutableLiveData()
}