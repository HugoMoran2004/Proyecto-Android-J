package com.example.proyectojunio.data.model


import retrofit2.http.GET
import retrofit2.http.Query

interface Service{
    @GET("trending/tv/day?language=en-US")
    fun listSeries(@Query("apiKey")apiKey : String): SerieResult
}