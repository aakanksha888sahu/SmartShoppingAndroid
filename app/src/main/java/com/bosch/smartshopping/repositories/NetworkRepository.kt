package com.bosch.smartshopping.repositories

import android.util.Base64
import com.bosch.smartshopping.backendapis.SmartShoppingService
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkRepository : NetworkIRepository {

    val smartShoppingRetrofitInstance = getRetrofitInstance().create(
        SmartShoppingService::class.java
    )

    /**
     * @since 1.0.0
     * @return retrofit instance
     */
    private fun getRetrofitInstance(): Retrofit {
        val domainUrl = "add_your_domain_url_here"
        /*val certificatePinner = CertificatePinner.Builder()
            .add(domainUrl, LEAF_CERTIFICATE_PUBLIC_KEY)
            .build()*/

        val okHttpClient = OkHttpClient().newBuilder()
            .addNetworkInterceptor(NetworkRequestInterceptor())
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(domainUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun getCartItems() {
        smartShoppingRetrofitInstance.getCartItems()
    }

    override fun addToCart(item: String, price: String, quantity: Int) {
        smartShoppingRetrofitInstance.addToCart(item, price, quantity)
    }

    override fun readInvoice() {
        TODO("Not yet implemented")
    }

    override fun validateQRcode() {
        TODO("Not yet implemented")
    }

    class NetworkRequestInterceptor() : Interceptor {

        /**
         * @since 1.0.0
         * @param chain
         * Intercepts request and include the needed headers.
         */
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val newRequest: Request
            newRequest = request.newBuilder()
                .addHeader("Authorization", getBasicAuth())
                .addHeader("x-cr-api-token", "add_your_api_token_here")
                .addHeader("Content-Type", "application/json") //most of the times, it is application/json
                .build()
            return chain.proceed(newRequest)
        }

        /**
         * @since 1.0.0
         * @return built basic auth string.
         */
        private fun getBasicAuth(): String {
            val username = "add_user_name_here"
            val password = "add_password_here"
            val credentials = "$username:$password"
            return "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        }
    }
}