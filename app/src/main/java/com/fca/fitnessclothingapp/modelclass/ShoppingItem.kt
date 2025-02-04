package com.fca.fitnessclothingapp.modelclass

import java.io.Serializable

data class ShoppingItem(
    val productName: String,
    val productRate: String,
    val productDesc: String,
    val productImage: Int
) : Serializable
