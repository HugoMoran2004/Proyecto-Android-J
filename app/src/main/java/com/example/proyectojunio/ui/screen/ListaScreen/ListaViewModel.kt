package com.example.proyectojunio.ui.screen.ListaScreen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectojunio.data.DAO.AppDB
import com.example.proyectojunio.data.DAO.SerieDao
import com.example.proyectojunio.data.model.Client
import com.example.proyectojunio.data.model.MediaItem
import com.example.proyectojunio.data.model.SerieDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch

class ListaViewModel(private val serieDao: SerieDao): ViewModel() {

    //Acceso a la base de datos
    //private val serieDao = AppDB.getDatabase(context).serieDao()

    //Lista de series
    private val _lista: MutableLiveData<List<SerieDb>> = MutableLiveData()
    val lista: LiveData<List<SerieDb>> = _lista

    //Serie seleccionada
    private val _specificSerie = MutableStateFlow<SerieDb?>(null)
    val specificSerie: StateFlow<SerieDb?> = _specificSerie.asStateFlow()

    private val _progressBar: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressBar: LiveData<Boolean> = _progressBar

    /*
    init {
        //Iniciamos barra de progreso y corrutina
        _progressBar.value = true
        viewModelScope.launch() {

            try {
                //Recuperamos todas las series de la bd y actualizamos el estado
                val allSeries = serieDao.getAllSeries()
                _lista.value = allSeries
            } catch (e: Exception) {
                //Error se carga la lista vacia y quitamos barra de progreso
                _lista.value = emptyList()
                _progressBar.value = false
            }
        }
    }*/

    fun carfarSeriesIniciales(context: Context){
        viewModelScope.launch{
            try{
                val existentes = serieDao.getAllSeries()
                if(existentes.isNotEmpty()){
                    _lista.value = existentes
                    _progressBar.value = false
                    return@launch
                }
                val response = Client.service.listSeries("8b8ac32d405cadf0c047407cc3e85541")
                val seriesDesdeApi = response.results ?: emptyList()

                seriesDesdeApi.forEach { serie ->
                    serieDao.insertSerie(serie)
                }

                val seriesActualizadas = serieDao.getAllSeries()
                _lista.value = seriesActualizadas
            } catch (e: Exception){
                _lista.value = emptyList()
                _progressBar.value = false
            } finally {
                _progressBar.value = false
            }
        }
    }

    fun getSerieById(id: Int){
        //Lanzamos corrutina
        viewModelScope.launch {
            try{
                val serie = serieDao.getSerieById(id)
                //Obtenemos la serie de la bd sino existe llamamos a la API
                if (serie != null){
                    //Actualizamos el estado
                    _specificSerie.value = serie
                } else{
                    //Llamada API pasando id y apikey
                    val response = Client.service.getSerieById( id,"8b8ac32d405cadf0c047407cc3e85541")
                    val serie = response.results?.firstOrNull()
                    //Si obtenemos la serie se guarda en la bd
                    serie?.let { serieDao.insertSerie(it) }
                    _specificSerie.value = serie
                }
            } catch (e: Exception){
                //Error actualiza el estado a null
                _specificSerie.value = null
            }
        }
    }

}