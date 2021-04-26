package com.shoh.ipakyolibankexample.data_state

import java.lang.Exception

sealed class DataState<out T> {

    data class Success<out V>(val data: V) : DataState<V>()
    data class Error(val exception: Exception) : DataState<Nothing>()

}