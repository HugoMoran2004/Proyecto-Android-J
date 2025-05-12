package com.example.proyectojunio.data.model

data class SerieResult(
    val page: Int,
    val results: List<SerieDb>,
    val total_pages: Int,
    val total_results: Int
)