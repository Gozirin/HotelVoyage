package com.example.hbapplicationgroupa.dependency_module.repository_moduls

import com.example.hbapplicationgroupa.database.dao.WishListDao
import com.example.hbapplicationgroupa.repository.wishListDaoRepository.WishListDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WishListDaoRepositorymodule {
    @Singleton
    @Provides
    fun provideWishListDaoRepository(wishListDao: WishListDao): WishListDaoRepository {
        return WishListDaoRepository(wishListDao)
    }
}