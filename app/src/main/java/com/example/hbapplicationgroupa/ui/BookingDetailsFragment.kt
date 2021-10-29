package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.common.BackgroundShapeType
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.theme.LightThemeFactory
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.databinding.FragmentBookingDetailsBinding
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemRoomTypes
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseItem
import com.example.hbapplicationgroupa.utils.*
import com.example.hbapplicationgroupa.viewModel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class BookingDetailsFragment: Fragment(), PeopleBottomSheetOnClickInterface, RoomTypeAdapterInterface {
    //Set up view binding here
    private var _binding: FragmentBookingDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: BookingDetailsFragmentArgs by navArgs()
    private val hotelViewModel: HotelViewModel by viewModels()
    private lateinit var fetchedRoomTypes: ArrayList<GetHotelByIdResponseItemRoomTypes>
    private lateinit var hotelName: String
    var hotelBookingInfo: BookHotel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBookingDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Show the calendar for date selection
        AuthPreference.initPreference(requireActivity())


        binding.checkInEditText.setOnClickListener {
            showDateRangePicker()
        }

        //Show the calendar for date selection
        binding.checkOutEditText.setOnClickListener {
            showDateRangePicker()
        }

        //Show bottom sheet holding number of people selection
        binding.peopleEditText.setOnClickListener {
            NumberOfPeopleBottomSheetDialogFragment(this).show(
                requireActivity().supportFragmentManager, "peopleBottomSheet"
            )
        }

        //Show bottom sheet holding rooms selection
        binding.roomsEditText.setOnClickListener {
            NumberOfRoomsBottomSheetDialogFragment(fetchedRoomTypes, this).show(
                requireActivity().supportFragmentManager, "roomTypeBottomSheet"
            )
        }

        binding.bookNowButton.setOnClickListener {
            if (!phoneNumberIsNotEmpty(binding.phoneTextInputEditText.text.toString())){
                binding.contactNumberTextInputLayout.error = "Kindly enter your phone number"
                return@setOnClickListener
            }else if (!phoneNumberEqualsLength(binding.phoneTextInputEditText.text.toString())){
                binding.contactNumberTextInputLayout.error = "Phone number must be 11 digits long"
                return@setOnClickListener
            }else if (!isAValidNigerianNumber(binding.phoneTextInputEditText.text.toString())){
                binding.contactNumberTextInputLayout.error = "Kindly enter a valid Nigerian phone number"
                return@setOnClickListener
            }else if (!checkInIsNotEmpty(binding.checkInEditText.text.toString())){
                binding.checkInTextInputLayout.error = "Kindly select your preferred check in date"
                return@setOnClickListener
            }else if (!checkOutIsNotEmpty(binding.nameTextInputEditText.text.toString())){
                binding.checkOutTextInputLayout.error = "Kindly select your preferred check out date"
                return@setOnClickListener
            } else if (!numberOfPeopleIsNotEmpty(binding.peopleEditText.text.toString())){
                binding.peopleTextInputLayout.error = "Kindly enter the total number of people to be lodged"
                return@setOnClickListener
            }else if (!roomTypeIsNotEmpty(binding.roomsEditText.text.toString())){
                binding.roomsTextInputLayout.error = "Kindly select your preferred rooms"
                return@setOnClickListener
            }
            else{
                pushBookHotelData()
//               findNavController().navigate(R.id.action_bookingDetailsFragment_to_paymentCheckoutFragment)
            }
        }

        binding.bookingDetailsBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        getRoomTypes()
        onBackPressed()
    }

    /*
    The prime date picker library was used to implement the bottom sheet calendar date range picker.
    Documentation is found in https://github.com/aminography/PrimeDatePicker.
    The themeFactory object controls the customization of the date range picker.
    Customization includes colours, fonts, drawables, etc.
     */
    private val themeFactory = object : LightThemeFactory(){
        override val pickedDayBackgroundShapeType: BackgroundShapeType
            get() = BackgroundShapeType.CIRCLE

        override val calendarViewPickedDayBackgroundColor: Int
            get() = getColor(R.color.splash_screen_background_color)

        override val calendarViewPickedDayInRangeBackgroundColor: Int
            get() = getColor(R.color.red)

        override val calendarViewPickedDayInRangeLabelTextColor: Int
            get() = getColor(R.color.black)

        override val calendarViewMonthLabelTextColor: Int
            get() = getColor(R.color.red)

        override val calendarViewTodayLabelTextColor: Int
            get() = getColor(R.color.red)

        override val calendarViewWeekLabelTextColors: SparseIntArray
            get() = SparseIntArray(7).apply {
                val black = getColor(android.R.color.black)
                put(Calendar.SATURDAY, black)
                put(Calendar.SUNDAY, black)
                put(Calendar.MONDAY, black)
                put(Calendar.TUESDAY, black)
                put(Calendar.WEDNESDAY, black)
                put(Calendar.THURSDAY, black)
                put(Calendar.FRIDAY, black)
            }

        override val calendarViewShowAdjacentMonthDays: Boolean
            get() = true

        override val selectionBarBackgroundColor: Int
            get() = getColor(R.color.splash_screen_background_color)

        override val selectionBarRangeDaysItemBackgroundColor: Int
            get() = getColor(R.color.splash_screen_background_color)
    }

    private fun showDateRangePicker() {
        val today = CivilCalendar(TimeZone.getDefault(), Locale.getDefault())
        val datePicker = PrimeDatePicker.bottomSheetWith(today)
            .pickRangeDays { startDay, endDay ->
                binding.checkInEditText.setText("${startDay.date} ${startDay.monthName}, ${startDay.year}")
                binding.checkOutEditText.setText("${endDay.date} ${endDay.monthName}, ${endDay.year}")

            }.applyTheme(themeFactory).build()

        datePicker.show(parentFragmentManager, "dateRange")
    }

    //Method to handle back press
    private fun onBackPressed(){
        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Receive data from number of people bottom sheet and populate it on the people edit text
    override fun passDataFromPeopleBottomSheetToBookingDetailsScreen(data: String) {
        binding.peopleEditText.setText(data)
    }

    //Make a network request to get room info and hotel name
    private fun getRoomTypes(){
        hotelViewModel.getHotelById(args.hotelId)
        hotelViewModel.getHotelFromDb().observe(viewLifecycleOwner, {
            it.forEach { response ->
                hotelName = response.name
                fetchedRoomTypes = response.roomTypes
            }

            if (it != null){
                binding.nameTextInputEditText.setText(hotelName)
            }
        })
    }

    //Receive data from rooms bottom sheet and populate it on the rooms edit text
    override fun getSelectedRoomTypes(position: Int, name: String) {
        binding.roomsEditText.setText(name)
    }


    private fun pushBookHotelData() {

            val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
//            hotelBookingInfo = BookHotel(
//                roomId,
//                binding.checkInEditText.text.toString(),
//                binding.checkOutEditText.text.toString(),
//                5,
//                ""
//            )
            hotelViewModel.pushBookHotel(authToken, hotelBookingInfo!!)
            hotelViewModel.bookingInfo.observe(viewLifecycleOwner, {
                if (it != null) {
                    Toast.makeText(requireContext(), "Booking Details Captured", Toast.LENGTH_LONG)
                        .show()
                    val action =
                        BookingDetailsFragmentDirections.actionBookingDetailsFragmentToPaymentCheckoutFragment(
                            hotelBookingInfo
                        )
                    findNavController().navigate(action)
                }
            })
        }
}