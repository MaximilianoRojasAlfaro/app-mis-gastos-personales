package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import cl.bootcamp.maximilianorojas.misgastospersonales.data.modelo.Gasto
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.BotonEnviar
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.InputTexto
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.TextoError
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MainViewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.NuevoGastoViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NuevoGasto(
    mainViewModel: MainViewModel,
    nuevoGastoViewModel: NuevoGastoViewModel
){
    val uiState by nuevoGastoViewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        InputTexto(
            valor = uiState.concepto,
            label = "Concepto",
            isError = uiState.errorConcepto != null,
            mensajeError = uiState.errorConcepto,
            keyboardType = KeyboardType.Text,
            onValueChange = { nuevoConcepto ->
                nuevoGastoViewModel.onChangeConcepto(nuevoConcepto)
            }
        )

        InputTexto(
            valor = uiState.monto,
            label = "Monto",
            isError = uiState.errorMonto != null,
            mensajeError = uiState.errorMonto,
            keyboardType = KeyboardType.Number,
            onValueChange = { nuevoMonto ->
                nuevoGastoViewModel.onChangeMonto(nuevoMonto)
            }
        )

        BotonEnviar(
            isEnabled = uiState.esFormularioValido,
            onClick = {

                mainViewModel.agregarGasto(
                    gasto = Gasto(
                        id = 0,
                        concepto = uiState.concepto,
                        monto = uiState.monto.toInt(),
                        fecha = LocalDate.now().toString()
                    )
                )

                nuevoGastoViewModel.limpiarFormulario()
            }
        )
    }
}