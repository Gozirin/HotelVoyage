package com.example.hbapplicationgroupa.utils

import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

object LoginValidations {

    //Method to validate login input fields if they are empty or not
    fun validateLoginFields(email: EditText, password: EditText, loginBtn: View){
        password.addTextChangedListener {
            val emails = email.text.toString().trim()
            loginBtn.enable(emails.isNotEmpty() && it.toString().isNotEmpty())
        }
    }

    fun View.enable(enabled: Boolean){
        isEnabled = enabled
        alpha = if (enabled) 1f else 0.5f
    }
}