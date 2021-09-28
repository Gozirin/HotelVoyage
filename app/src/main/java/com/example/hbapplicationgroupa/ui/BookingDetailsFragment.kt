package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.common.BackgroundShapeType
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.theme.LightThemeFactory
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentBookingDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class BookingDetailsFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentBookingDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBookingDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkInEditText.setOnClickListener {
            showDateRangePicker()
        }

        binding.checkOutEditText.setOnClickListener {
            showDateRangePicker()
        }

        binding.peopleEditText.setOnClickListener {
            peopleBottomSheet()
        }

        binding.roomsEditText.setOnClickListener {
            roomsBottomSheet()
        }

        binding.bookNowButton.setOnClickListener {
            findNavController().navigate(R.id.action_bookingDetailsFragment_to_bookingConfirmationFragment)
        }
    }

    //Inflate fragment_number_of_people_bottom_sheet_dialog as bottom sheet dialog
    private fun peopleBottomSheet(){
        val bottomSheet = context?.let { BottomSheetDialog(it) }
        val view = layoutInflater.inflate(R.layout.fragment_number_of_people_bottom_sheet_dialog, null)
        bottomSheet?.setContentView(view)
        bottomSheet?.show()
    }

    //Inflate fragment_number_of_rooms_bottom_sheet_dialog as bottom sheet dialog
    private fun roomsBottomSheet(){
        val bottomSheet = context?.let { BottomSheetDialog(it) }
        val view = layoutInflater.inflate(R.layout.fragment_number_of_rooms_bottom_sheet_dialog, null)
        bottomSheet?.setContentView(view)
        bottomSheet?.show()
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

            }.applyTheme(themeFactory).build()

        fragmentManager?.let {
            datePicker.show(it, "dateRange")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}