package com.example.hbapplicationgroupa

import android.widget.EditText
import androidx.core.widget.addTextChangedListener

//
  var errorMessage = ""
  var result = true


// function to check Non-empty new password field
    fun validateNotEmptyNewPasswordField(newPassword: String): Boolean{

    return newPassword.isNotEmpty()
    }


 //function to check Non-empty confirm password field
 fun validateNotEmptyConfirmPasswordField(confirmPassword: String): Boolean{
    result = confirmPassword.isNotEmpty()
    return result
  }



//
//    fun validatePassword(newPassword: EditText, confirmPassword: EditText) : Boolean{
//        val newPass = newPassword.text.toString().trim()
//
//        confirmPassword.addTextChangedListener {
//            result = it.toString().isNotEmpty() && newPass.isNotEmpty()
//        }
//        return result
//    }



// function for regex validation
  fun validateNewPassword(newPassword: String): Boolean{
   //  val checkedNewPassword = Regex("^(?=.*[A-Z].*[A-Z])(?=.*[!@#\$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}\$")

    val checkedNewPassword = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\\^&\\*]).{8,}\$")
    result = newPassword.matches(checkedNewPassword)
       return result
  }

 //function to check if the two fields are  equal
  fun validateNewPasswordAndConfirmPassword(newPassword: String,confirmPassword: String): Boolean{
    result = newPassword == confirmPassword
    return result
 }
