package com.falou.avaliacao_tecnica_falou.data.remote

import com.falou.avaliacao_tecnica_falou.data.model.Dictionary
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface OxfordService {
    
    @GET("entries/{source_lang}/{word_id}")
    suspend fun getTranslation(
        @Header("app_id") app_id : String = APP_ID,
        @Header("app_key") app_key: String = APP_KEY,
        @Path("source_lang") source_lang: String,
        @Path("word_id") word_id: String,
        ): Response<Dictionary>

    companion object {
        private const val APP_ID = "7f03048a"
        private const val APP_KEY =  "757c6b8a64baccbf19d94b4195d14755"
    }
}