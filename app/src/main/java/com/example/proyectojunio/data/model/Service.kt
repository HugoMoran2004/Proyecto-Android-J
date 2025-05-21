package com.example.proyectojunio.data.model


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service{
    @GET("trending/tv/day?language=en-US")
   suspend fun listSeries(@Query("api_key")apiKey : String): SerieResult

   @GET("tv/{tv_id}?language=en-US")
   suspend fun getSerieById(@Path("tv_id") tvId: Int,
       @Query("api_key")apiKey : String): SerieResult
}