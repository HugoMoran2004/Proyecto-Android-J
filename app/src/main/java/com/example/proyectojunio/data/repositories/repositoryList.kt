package com.example.proyectojunio.data.repositories

import com.example.proyectojunio.data.model.MediaItem
import com.example.proyectojunio.data.model.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

object repositoryList {

    suspend fun getMedia(nombre: String): List<MediaItem>{
        var lista = mutableListOf<MediaItem>()
        withContext(Dispatchers.IO){
            delay(2000)
            lista = (1..100).map {
                MediaItem(
                    id = it,
                    title = "Titulo $it",
                    photo = "",
                    tipo = if (it % 3 == 0) Type.VIDEO else Type.FOTO
                )
            } as MutableList<MediaItem>
        }
        return lista
    }
    fun addMedia(mediaItem: MediaItem) {
        // Implementación para agregar un nuevo elemento de medios
    }

}