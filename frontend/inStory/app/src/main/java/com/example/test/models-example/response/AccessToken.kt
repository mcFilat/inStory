package com.example.test.`models-example`.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccessToken (
        val access: String
)
