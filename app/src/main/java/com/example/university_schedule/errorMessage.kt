package com.example.university_schedule

import com.google.android.material.textfield.TextInputLayout

fun showErrorMessage(item : TextInputLayout, message: String) {
    item.error = message
}