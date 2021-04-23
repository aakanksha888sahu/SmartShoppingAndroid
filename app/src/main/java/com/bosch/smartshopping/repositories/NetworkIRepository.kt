package com.bosch.smartshopping.repositories

interface NetworkIRepository {

    fun getCartItems()
    fun addToCart(item: String, price: String, quantity: Int)
    fun readInvoice()
    fun validateQRcode()
}