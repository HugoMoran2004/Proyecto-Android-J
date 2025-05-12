package com.example.proyectojunio.data.model

import retrofit2.Retrofit

object Client {

    private val retrofit= Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .build()

    val service = retrofit.create(Service::class.java)
}
//kjnjhjohjkdfdsfdsfdsf