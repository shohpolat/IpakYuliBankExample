package com.shoh.ipakyolibankexample.model

data class Card(
    val id: String,
    val name: String,
    val cardHolder: String,
    val cardType: String,
    val cardNumber: String,
    val cardBalance: Int
)