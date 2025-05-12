package com.example.proyectojunio.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.proyectojunio.ui.screen.DetalleScreen.DetailScreen
import com.example.proyectojunio.ui.screen.ListaScreen.ListaScreen
import com.example.proyectojunio.ui.screen.ListaScreen.ListaViewModel

@Composable
fun Navegacion(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Lista){
        composable<Lista> {
            val viewModel = ListaViewModel()
            ListaScreen(viewModel) { id ->
                navController.navigate(Detail(id))
            }
        }
        composable<Detail> { backStackEntry ->
            val detail = backStackEntry.toRoute<Detail>()
            val id = detail.id
            DetailScreen(id)
        }
    }
}