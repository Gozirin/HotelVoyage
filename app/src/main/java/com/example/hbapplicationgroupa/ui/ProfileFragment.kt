package com.example.hbapplicationgroupa.ui

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.UploadRequestBody
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.databinding.FragmentProfileBinding
import com.example.hbapplicationgroupa.utils.TO_READ_EXTERNAL_STORAGE
import com.example.hbapplicationgroupa.utils.getFileName
import com.example.hbapplicationgroupa.utils.snackbar
import com.example.hbapplicationgroupa.viewModel.AuthViewModel
import com.example.hbapplicationgroupa.viewmodel.CustomerViewModel
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ProfileFragment : Fragment(), UploadRequestBody.uploadCalback  {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: Dialog
    private var selectedImage: Uri? = null
    private lateinit var authPreference: AuthPreference
    private lateinit var viewModel: CustomerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        authPreference = AuthPreference(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = Dialog(requireContext())

        readStorage()

        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_profileFragment_to_exploreHomeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.fragmentProfileBookingsCon.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_pastBookingsFragment2)
        }

        binding.fragmentProfileHelpCon.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_helpAndSupportFragment)
        }

        binding.fragmentProfilePrivacyCon.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_privacyPolicyFragment)
        }

        binding.fragmentProfileLogoutTv.setOnClickListener {
            dialog.setContentView(R.layout.log_out_dialogbox)
            dialog.show()
            dialogActivities()
        }

        //  listener to pick image from gallery
        binding.ivImageProfile.setOnClickListener {
            openImageChooser()
        }

    }

    //Method to logout by clearing authToken from sharedPreference
    private fun dialogActivities(){
        //logout
        val logout = dialog.findViewById<TextView>(R.id.dialogLogout)
        logout.setOnClickListener {
            authPreference.clear("token_key")
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            dialog.dismiss()
        }

            //cancel log out event
            val cancel = dialog.findViewById<TextView>(R.id.dialogCancel)
            cancel.setOnClickListener {
                dialog.dismiss()
            }
        }


    // this method allow the user to pick image
    private fun openImageChooser(){
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
        }
    }

    companion object{
        private const val  REQUEST_CODE_IMAGE_PICKER = 100
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun uploadImage() {
        if (selectedImage == null) {
            binding.fragmentProfilePage.snackbar("select an Image first")
            return
        }

        val parcelFileDescriptor = context?.contentResolver?.openAssetFileDescriptor(selectedImage!!, "r", null)?: return
        val file = File(requireActivity().cacheDir, requireActivity().contentResolver.getFileName(selectedImage!!))
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream )
        binding.progressCircular.progress = 0
      //  val body = UploadRequestBody(file, "image", this )
    }

    override fun onProgressUpdate(percentage: Int) {
        binding.progressCircular .progress = percentage
    }

    // method to make Api call for updating image
//    fun makeApiCallToUpdateImage(){
//        viewModel.makeApiCall()
//    }


    //method to observe the for updating image
    fun observeNetwork(){
      viewModel.updateProfileImageLiveData.observe(viewLifecycleOwner,{
        if (it == null){
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        }else{
          binding.progressCircular.progress = 100
            binding.fragmentProfilePage.snackbar("okay")
        }
      })

    }



    // this is the function that check if the request is  granted
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_IMAGE_PICKER ->{
                    selectedImage = data?.data
                    binding.ivImageUserProfile.setImageURI(selectedImage)

                }
            }
        }

    }



    private fun readStorage(){
        if (ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                TO_READ_EXTERNAL_STORAGE
            )
        }
    }

   //this is the function that request for permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == TO_READ_EXTERNAL_STORAGE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                visibilityPermission()
            }else{
               // messages.visibility = View.VISIBLE
              //  button. visibility = View.VISIBLE

//
            }
        }
    }


    //// this is the function that set the permission visibility
    private fun visibilityPermission(){
       // messages.visibility = View.GONE
        //button.visibility = View.GONE
        openImageChooser()
    }
}



