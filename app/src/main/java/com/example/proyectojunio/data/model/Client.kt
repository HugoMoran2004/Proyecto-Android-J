package com.example.proyectojunio.data.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Client {

    private val retrofit= Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service:Service = retrofit.create()
}
//kjnjhjohjkdfdsfdsfdsf