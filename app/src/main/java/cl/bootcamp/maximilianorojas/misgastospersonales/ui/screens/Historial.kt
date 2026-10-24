package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.TarjetaHistorial
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.TextoTotalGastos
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun Historial(
    mainViewModel: MainViewModel
) {

    val uiState by mainViewModel.uiState.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
        content = {

            item {
                TextoTotalGastos(texto = "Total: $${mainViewModel.obtenerTotalGastos()}")
            }

            items(uiState.listaGastos) { gasto ->

                TarjetaHistorial(
                    concepto = gasto.concepto,
                    fecha = mainViewModel.formatearFechaParaVista(gasto.fecha),
                    monto = "$${gasto.monto}",
                    onBotonEliminarClick = {
                        mainViewModel.eliminarGasto(gasto)
                    }
                )
            }
        }
    )
}