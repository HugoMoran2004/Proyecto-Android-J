package com.example.proyectojunio.ui.screen.ListaScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectojunio.data.model.Client
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
            // _lista.value = repositoryList.getMedia("Seville")
            //_lista.value = Client.service.listSeries("8b8ac32d405cadf0c047407cc3e85541") //poner apiKey
            val series = Client.service.listSeries("8b8ac32d405cadf0c047407cc3e85541")
            _lista.value = series.results.map {
                MediaItem(
                    it.id,
                    it.name,
                    "https://image.tmdb.org/t/p/w185" + it.poster_path

                )
            }
            _progressBar.value = false
        }
    }
}