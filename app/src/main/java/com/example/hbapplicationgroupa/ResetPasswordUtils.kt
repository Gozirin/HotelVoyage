package com.example.hbapplicationgroupa

import android.widget.EditText
import androidx.core.widget.addTextChangedListener

object ResetPassword{
//
var errorMessage = ""

    var result = false


    fun validateField (newPassword: String, confirmPassword: String) : Boolean {
        if (newPassword.isEmpty() && confirmPassword.isEmpty()){
            result = false
            errorMessage = "empty"
        }else{
            result = true
        }
        return result
    }


    fun validatePassword(newPassword: EditText, confirmPassword: EditText) : Boolean{
        val newPass = newPassword.text.toString().trim()

        confirmPassword.addTextChangedListener {
            result = it.toString().isNotEmpty() && newPass.isNotEmpty()
        }
        return result
    }



}

