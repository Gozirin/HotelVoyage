package com.example.hbapplicationgroupa.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.hbapplicationgroupa.database.HotelDatabase
import com.example.hbapplicationgroupa.getOrAwaitValue
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemData
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemReviews
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemRoomTypes
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class HotelByIdDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var hotelDatabase: HotelDatabase
    private lateinit var hotelByIdDao: HotelByIdDao

    @Before
    fun setUp(){
        hotelDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            HotelDatabase::class.java
        ).allowMainThreadQueries().build()

        hotelByIdDao = hotelDatabase.getHotelByIdDao()
    }

    @After
    fun tearDownDb(){
        hotelDatabase.close()
    }

    @Test
    fun addHotel() = runBlocking {
        val gallery = arrayListOf("link one", "link two")
        val roomTypes1 = GetHotelByIdResponseItemRoomTypes("one", "Single", "A single room", 600.0f, 466.44f, "thumbnails")
        val reviews1 = GetHotelByIdResponseItemReviews("Great hotel", "image uri", "15-10-21")
        val roomTypes = arrayListOf(roomTypes1)
        val reviews = arrayListOf(reviews1)
        val hotel = GetHotelByIdResponseItemData(
            1, "sss", "Little hotel", "Little hotel description", "littlehotel@gmail.com", "07067991832",
            "12 Edo street", "Benin", "Edo", 5.0f, "imageLink", gallery, roomTypes, reviews
        )

        hotelByIdDao.insertHotel(hotel)

        val allHotels = hotelByIdDao.getHotelById().getOrAwaitValue()

        assertThat(allHotels).contains(hotel)
    }
}