package com.example.proyectojunio.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.proyectojunio.data.DAO.AppDB
import com.example.proyectojunio.ui.screen.DetalleScreen.DetailScreen
import com.example.proyectojunio.ui.screen.ListaScreen.ListaScreen
import com.example.proyectojunio.ui.screen.ListaScreen.ListaViewModel
import com.example.proyectojunio.ui.screen.ListaScreen.ViewModelFactory

@Composable
fun Navegacion(){
    val navController = rememberNavController()
    val context = LocalContext.current

    val serieDao = AppDB.getDatabase(context).serieDao()

    val viewModel: ListaViewModel = viewModel(
        factory = ViewModelFactory(serieDao)
    )
    NavHost(navController = navController, startDestination = Lista){
        composable<Lista> {
            ListaScreen(viewModel) { id ->
                navController.navigate(Detail(id))
            }
        }
        composable<Detail> { backStackEntry ->
            val detail = backStackEntry.toRoute<Detail>()
            val id = detail.id
            DetailScreen(
                id = id,
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                })
        }

        /*composable<Busqueda> {
            BusquedaScreen(
                viewModel = viewModel,

            )
        }*/
    }
}