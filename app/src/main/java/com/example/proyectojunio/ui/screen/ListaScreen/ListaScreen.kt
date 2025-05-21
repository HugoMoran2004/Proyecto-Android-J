package com.example.proyectojunio.ui.screen.ListaScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.example.proyectojunio.data.model.MediaItem
import coil.compose.rememberAsyncImagePainter
import com.example.proyectojunio.data.model.SerieDb
import com.example.proyectojunio.data.model.Type



@Composable
fun ListaScreen(viewModel: ListaViewModel, navigateToDetail: (Int) -> Unit) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.carfarSeriesIniciales(context)
    }
    val lista by viewModel.lista.observeAsState(emptyList())
    val progressBar by viewModel.progressBar.observeAsState(false)

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black)
    )

    if (progressBar) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {


        if (lista!!.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay elementos", style = MaterialTheme.typography.bodySmall, color = Color.White)
            }
        } else {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                items(lista!!) { mediaItem ->
                    MediaListItem(mediaItem, navigateToDetail)
                }
            }
        }
    }

}

@Composable
private fun MediaListItem(mediaItem: SerieDb, navigateToDetail: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(250.dp)
            .clickable { navigateToDetail(mediaItem.id) },
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Imagen(item = mediaItem)
        Spacer(modifier = Modifier.height(5.dp))
        Title(item = mediaItem)
    }
}

@Composable
fun Imagen(item: SerieDb, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val imageUrl = "https://image.tmdb.org/t/p/w185/${item.poster_path}"

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl,
                imageLoader = ImageLoader.Builder(context).crossfade(true).build()
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


    }
}



@Composable
fun Title(item: SerieDb) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(5.dp)
    ) {
        Text(
            text = item.original_name,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2
        )
    }
}