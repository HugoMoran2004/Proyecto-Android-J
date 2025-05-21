package com.example.proyectojunio.ui.screen.DetalleScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import com.example.proyectojunio.ui.screen.ListaScreen.ListaViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(id:Int, viewModel: ListaViewModel, onBackClick: () -> Unit){


    val selectedSerie by viewModel.specificSerie.collectAsState()
    selectedSerie?.let {serie ->
        Column{
            Text(text = serie.name)
            Text(text = serie.first_air_date)
            Text(text= serie.overview)
            AsyncImage(model =  "https://image.tmdb.org/t/p/w185/${serie.poster_path}", contentDescription = serie.name)

        }

    }
    LaunchedEffect(id) {
        viewModel.getSerieById(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        selectedSerie?.let { serie ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w185/${serie.poster_path}",
                    contentDescription = serie.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = serie.name,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = serie.overview ,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }
}




