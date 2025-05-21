package com.example.proyectojunio.ui.screen.ListaScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectojunio.data.DAO.SerieDao

class ViewModelFactory(private val serieDao: SerieDao) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListaViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ListaViewModel(serieDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}