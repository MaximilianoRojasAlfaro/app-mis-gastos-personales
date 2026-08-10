package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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