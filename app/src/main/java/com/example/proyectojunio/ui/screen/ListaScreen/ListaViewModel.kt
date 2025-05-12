package com.example.proyectojunio.ui.screen.ListaScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectojunio.data.model.MediaItem
import com.example.proyectojunio.data.repositories.repositoryList
import kotlinx.coroutines.launch

class ListaViewModel: ViewModel() {
    private val _lista: MutableLiveData<List<MediaItem>> = MutableLiveData()
    val lista: LiveData<List<MediaItem>> = _lista

    private val _progressBar: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressBar: LiveData<Boolean> = _progressBar

    init {
        _progressBar.value = true
        viewModelScope.launch() {
            _lista.value = repositoryList.getMedia("Seville")
            _progressBar.value = false
        }
    }
}