package com.example.hbapplicationgroupa.viewmodel


import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HotelViewModelTest {

//    @get: Rule
//    var testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
//
//    @get: Rule
//    var testCoroutineRule = TestCoroutineRule()
//
//    @Mock
//    private lateinit var hotelRepositoryInterface: HotelRepositoryInterface
//
//    @Mock
//    private lateinit var hotelDatabaseRepositoryInterface: HotelDatabaseRepositoryInterface
//
//    @Mock
//    private lateinit var topHotelsObserver: Observer<Resource<List<GetTopHotelsResponseItem>>>
//
//    @Mock
//    private lateinit var topDealsObserver: Observer<Resource<List<GetTopDealsResponseItem>>>
//
//
//    @Before
//
//    fun setUp() {
//
//    }
//
//
//    @Test
//    fun `get top hotels request when no network should return error`() {
//        testCoroutineRule.runBlockingTest {
//            val errorMsg = "Network Error"
//            doThrow(RuntimeException(errorMsg))
//                .`when`(hotelRepositoryInterface)
//                .getTopHotels()
//            val viewModel = HotelViewModel(hotelRepositoryInterface, hotelDatabaseRepositoryInterface)
//            viewModel.getTopHotels().observeForever(topHotelsObserver)
//            verify(hotelRepositoryInterface).getTopHotels()
//            verify(topHotelsObserver).onChanged(Resource.error(RuntimeException(errorMsg).toString(), null))
//            viewModel.getTopHotels().removeObserver(topHotelsObserver)
//        }
//    }
//
//
//    @Test
//    fun `get top hotels request when network is available should return success`() {
//        testCoroutineRule.runBlockingTest {
//            doReturn(emptyList<GetTopHotelsResponseItem>())
//                .`when`(hotelRepositoryInterface)
//                .getTopHotels()
//            val viewModel = HotelViewModel(hotelRepositoryInterface, hotelDatabaseRepositoryInterface)
//            viewModel.getTopHotels().observeForever(topHotelsObserver)
//            verify(hotelRepositoryInterface).getTopHotels()
//            verify(topHotelsObserver).onChanged(Resource.success(emptyList()))
//            viewModel.getTopHotels().removeObserver(topHotelsObserver)
//        }
//    }
//
//
//    @Test
//    fun `get top deals request when no network should return error`() {
//        testCoroutineRule.runBlockingTest {
//            val errorMsg = "Network Error"
//            doThrow(RuntimeException(errorMsg))
//                .`when`(hotelRepositoryInterface)
//                .getTopDeals()
//            val viewModel = HotelViewModel(hotelRepositoryInterface, hotelDatabaseRepositoryInterface)
//            viewModel.getTopDeals().observeForever(topDealsObserver)
//            verify(hotelRepositoryInterface).getTopDeals()
//            verify(topDealsObserver).onChanged(Resource.error(RuntimeException(errorMsg).toString(), null))
//            viewModel.getTopDeals().removeObserver(topDealsObserver)
//        }
//    }
//
//
//    @Test
//    fun `get top deals request when network is available should return success`() {
//        testCoroutineRule.runBlockingTest {
//            doReturn(emptyList<GetTopDealsResponseItem>())
//                .`when`(hotelRepositoryInterface)
//                .getTopDeals()
//            val viewModel = HotelViewModel(hotelRepositoryInterface, hotelDatabaseRepositoryInterface)
//            viewModel.getTopDeals().observeForever(topDealsObserver)
//            verify(hotelRepositoryInterface).getTopDeals()
//            verify(topDealsObserver).onChanged(Resource.success(emptyList()))
//            viewModel.getTopDeals().removeObserver(topDealsObserver)
//        }
//    }
//
//
//
//    @After
//    fun tearDown() {
//
//    }
}