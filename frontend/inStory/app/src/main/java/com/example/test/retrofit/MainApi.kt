package com.example.test.retrofit

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {
    @Headers("Content-Type: application/json")
    @GET("auth/product/{id}")
    suspend fun getProductById(
        @Header("Authorization") token: String,
        @Path("id") id:Int
    ): Product

    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest): User

//    @Headers("Content-Type: application/json")
//    @POST("auth/products/search")
//    suspend fun getProductsByNameAuth(@Header("Authorization") token: String,
//                                 @Query("q") name: String): Products
//
//    @Headers("Content-Type: application/json")
//    @POST("auth/products/search")
//    suspend fun getProductsByName(@Query("q") name: String): Products

}