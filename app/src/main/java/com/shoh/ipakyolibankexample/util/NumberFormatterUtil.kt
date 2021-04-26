package com.shoh.ipakyolibankexample.util

import com.shoh.ipakyolibankexample.constants.constants
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NumberFormatterUtil
@Inject
constructor() {

    fun formatNumber(string: Int): String {
        val decimalFormat = DecimalFormat(constants.numberFormat)
        return decimalFormat.format(string).replace(",", " ")
    }

    fun formatNumber(string: Double): String {
        val decimalFormat = DecimalFormat(constants.numberFormat)
        return decimalFormat.format(string).replace(",", " ")
    }

    fun hideFirst4(string: String): String {
        var string2 = ""
        string.forEachIndexed { index, c ->

            string2 += if (index < 4) {
                "*"
            } else {
                string[index].toString()
            }

        }
        return string2
    }

    fun hideMiddle4(string: String): String {
        var string2 = ""
        string.forEachIndexed { index, _ ->

            string2 += if (index in 2..5) {
                "*"
            } else {
                string[index].toString()
            }
        }
        return string2
    }

    fun currentDate(date_format: String): String {
        return SimpleDateFormat(date_format, Locale.getDefault()).format(Date())
    }

}