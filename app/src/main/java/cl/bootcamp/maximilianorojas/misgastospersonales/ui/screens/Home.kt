package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screens

import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.CuerpoHome
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MainViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(
    mainViewModel: MainViewModel
){
    val uiState by mainViewModel.uiState.collectAsState()

    val modoHorizontal = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    //Control del modo horizontal para no romper la interfaz al rotar el telefono
    if(!modoHorizontal){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){

            CuerpoHome(
                tituloPrimeraTarjeta = "Gastos totales por mes:",
                tituloSegundaTarjeta = "Gastos más altos del mes:",
                listaResumenMes = mainViewModel.obtenerGastosPorMes(uiState.listaGastos),
                listaGastosDelMes = mainViewModel.obtenerGastosMasAltosDelMes(LocalDate.now().monthValue, uiState.listaGastos),
                modifier = Modifier.weight(.5f).padding(16.dp)
            )
        }

    } else {

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){

            CuerpoHome(
                tituloPrimeraTarjeta = "Gastos totales por mes:",
                tituloSegundaTarjeta = "Gastos más altos del mes:",
                listaResumenMes = mainViewModel.obtenerGastosPorMes(uiState.listaGastos),
                listaGastosDelMes = mainViewModel.obtenerGastosMasAltosDelMes(LocalDate.now().monthValue, uiState.listaGastos),
                modifier = Modifier.weight(.5f).padding(16.dp)
            )
        }
    }
}