package com.vnpay.hainv4.define

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.vnpay.hainv4.R


class GenericTextWatcher(view: View, editText: List<EditText>) : TextWatcher {
    private val view = view
    private val editText = editText
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(editable: Editable?) {
        val text = editable.toString()
        when (view.id) {
            R.id.edt_otp1 -> if (text.length == 1) {
                editText[1].requestFocus()
            }

            R.id.edt_otp2 -> {
                if (text.length == 1) {
                    editText[2].requestFocus()
                } else if (text.isEmpty()) {
                    editText[0].requestFocus()
                }
            }
            R.id.edt_otp3 -> {
                if (text.length == 1) {
                    editText[3].requestFocus()
                } else if (text.isEmpty()) {
                    editText[1].requestFocus()
                }
            }
            R.id.edt_otp4 -> {
                if (text.length == 1) {
                    editText[4].requestFocus()
                } else if (text.isEmpty()) {
                    editText[2].requestFocus()
                }
            }
            R.id.edt_otp5 -> {
                if (text.length == 1) {
                    editText[5].requestFocus()
                } else if (text.isEmpty()) {
                    editText[3].requestFocus()
                }
            }
            R.id.edt_otp6 -> {
                if (text.isEmpty()) {
                    editText[4].requestFocus()
                }
            }

        }
    }
}