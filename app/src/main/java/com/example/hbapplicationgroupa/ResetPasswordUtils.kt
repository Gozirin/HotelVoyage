package com.example.hbapplicationgroupa

import android.widget.EditText
import androidx.core.widget.addTextChangedListener

//
  var errorMessage = ""
  var result = false


// function to check Non-empty new password field
    fun validateNotEmptyNewPasswordField(newPassword: String): Boolean{
        result = newPassword.isNotEmpty()
        return result
    }


// function to check Non-empty confirm password field
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
     val checkedNewPassword = Regex("[A-Za-z][A-Za-z0-9@\$!%,.*?&]{8,}$")
     result = newPassword.equals(checkedNewPassword)
     return result
  }

// function to check if the two fields are  equal
  fun validateNewPasswordAndConfirmPassword(newPassword: String,confirmPassword: String): Boolean{
    result = newPassword == confirmPassword
    return result
 }
