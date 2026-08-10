package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.BotonEnviar
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.InputTexto
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.TextoError
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.NuevoGastoViewModel
import kotlinx.coroutines.launch

@Composable
fun NuevoGasto(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: NuevoGastoViewModel = viewModel()
){

    var concepto by remember { mutableStateOf(viewModel.concepto) }
    var monto by remember { mutableStateOf(viewModel.monto) }

    val scope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        InputTexto(
            valor = viewModel.concepto ?: "",
            label = "Concepto",
            isError = viewModel.errorConcepto != null,
            keyboardType = KeyboardType.Text,
            onValueChange = { viewModel.cambiarConcepto(it) }
        )

        viewModel.errorConcepto?.let { mensaje ->
            TextoError(mensaje)
        }

        InputTexto(
            valor = viewModel.monto ?: "",
            label = "Monto",
            isError = viewModel.errorMonto != null,
            keyboardType = KeyboardType.Number,
            onValueChange = { viewModel.cambiarMonto(it) }
        )

        viewModel.errorMonto?.let { mensaje ->
            TextoError(mensaje)
        }

        BotonEnviar(
            isEnabled = viewModel.esFormularioValido,
            onClick = {

                viewModel.agregarGasto()

                concepto = viewModel.eliminarConcepto()
                monto = viewModel.eliminarMonto()

                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "¡Gasto registrado!",
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        )
    }
}