package com.bosch.smartshopping.backendapis

import com.bosch.smartshopping.model.CartItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface SmartShoppingService {

    @PUT("/api/cart/{item}/{price}/{quantity}")
    fun addToCart(
        @Path("item") item: String,
        @Path("price") price: String,
        @Path("quantity") quantity: Int

    ): Call<Void>

    @GET("/api/cart")
    fun getCartItems(
    ): List<CartItem>
    //Call<Response>

}