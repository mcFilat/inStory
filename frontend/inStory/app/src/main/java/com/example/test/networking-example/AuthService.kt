package com.example.test.`networking-example`

import com.example.test.`models-example`.body.Login
import com.example.test.`models-example`.body.RefreshToken
import com.example.test.`models-example`.response.AccessToken
import com.example.test.`models-example`.response.BothTokens
import com.example.test.`models-example`.response.Ping
import com.example.test.`networking-example`.auth.AccessTokenAuthenticator
import com.example.test.`networking-example`.auth.AuthManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    // GET Requests

    @GET("ping/")
    suspend fun ping(
            @Query("id") id : Int
    ) : Response<Ping>

    // POST Requests

    @POST("/auth/jwt/create/")
    suspend fun access(
            @Body access: RefreshToken
    ) : Response<AccessToken>

    @POST("/auth/signup")
    suspend fun login(
            @Body credentials: Login
    ) : Response<BothTokens>

    // This is apparently a singleton in Kotlin
    companion object {
        fun create(authManager: AuthManager): AuthService {
            val baseUrl = "http://192.168.0.1:8000/"

            // Extra Logging - Warning: Will erase your HeaderInterceptor which is NOT good
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient().newBuilder()
                    .authenticator(AccessTokenAuthenticator(authManager))
                    .addInterceptor(HeaderInterceptor(authManager))
                    .addInterceptor(loggingInterceptor)
                    .build()

            // Create the service
            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build()
            return retrofit.create(AuthService::class.java)
        }
    }
}