package com.shoh.ipakyolibankexample.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat
import java.text.ParseException
import javax.inject.Inject

class Spacer
@Inject
constructor() : TextWatcher {

    private lateinit var et: EditText

    fun set(et: EditText) {
        this.et = et
    }

    private var df: DecimalFormat? = null
    private var dfnd: DecimalFormat? = null
    private var hasFractionalPart = false

    init {
        df = DecimalFormat("#,###.##")
        df!!.isDecimalSeparatorAlwaysShown = true
        dfnd = DecimalFormat("#,###")
        hasFractionalPart = false
    }

    private val TAG = "NumberTextWatcher"

    override fun afterTextChanged(s: Editable) {
        if (df != null) {
            et.removeTextChangedListener(this)
            try {
                val inilen: Int = et.text.length
                val v: String = s.toString().replace(
                    java.lang.String.valueOf(
                        df!!.decimalFormatSymbols.groupingSeparator
                    ), ""
                )
                val n: Number = df!!.parse(v)
                val cp = et.selectionStart
                if (hasFractionalPart) {
                    val formatted = df!!.format(n).replace(",", " ")
                    et.setText(formatted)
                } else {
                    val formatted = dfnd!!.format(n).replace(",", " ")
                    et.setText(formatted)
                }
                val endlen: Int = et.text.length
                val sel = cp + (endlen - inilen)
                if (sel > 0 && sel <= et.text.length) {
                    et.setSelection(sel)
                } else {
                    // place cursor at the end?
                    et.setSelection(et.text.length)
                }
            } catch (nfe: NumberFormatException) {
                // do nothing?
            } catch (e: ParseException) {
                // do nothing?
            }
            et.addTextChangedListener(this)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        hasFractionalPart =
            s.toString().contains(
                java.lang.String.valueOf(
                    df!!.decimalFormatSymbols.decimalSeparator
                )
            )
    }
}