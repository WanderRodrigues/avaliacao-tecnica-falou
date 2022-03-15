package com.falou.avaliacao_tecnica_falou.service

import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory.create
import java.net.URI.create

suspend fun <T> safeApiCall(call: suspend () -> Response<T>): ResponseAny<T> {
    return try {
        ResponseAny.create(call())
    } catch (e: Exception) {
        ResponseAny.create(e)
    }
}