package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.BotonEliminarGasto
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.TarjetaHistorial
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.TextoTotalGastos
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.NuevoGastoViewModel
import kotlinx.coroutines.launch

@Composable
fun Historial(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: NuevoGastoViewModel = viewModel()
) {

    val scope = rememberCoroutineScope()

    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize(),
        content = {

            item {
                TextoTotalGastos(texto = "Total: $${viewModel.obtenerMontoTotal()}")
            }

            items(viewModel.listaGastos) { gasto ->

                TarjetaHistorial(
                    concepto = gasto.concepto,
                    fecha = "${viewModel.formatearFechaParaVista(gasto.fecha)} - ${gasto.hora}",
                    monto = "$${gasto.monto.toInt()}",
                    onBotonEliminarClick = {
                        viewModel.eliminarGasto(gasto)

                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Gasto ${gasto.concepto} eliminado",
                                withDismissAction = true,
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                )
            }
        }
    )



}