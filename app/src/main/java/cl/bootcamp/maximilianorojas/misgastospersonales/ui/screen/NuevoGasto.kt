package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.NuevoGastoViewModel
import kotlinx.coroutines.launch

@Composable
fun NuevoGasto(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: NuevoGastoViewModel = viewModel()
){
    //Formulario para poder agregar un nuevo gasto
    //Debe tener como TextInput el concepto y el monto, y un botón grande para guardar el gasto.
    //Incluir iconos para mayor claridad
    //Concepto Reactividad: No puede estar vacío, no deberían ser solo números. Límite de caracteres, no es una descripción
    //Monto Reactividad: No puede estar vacío. Solo enteros/reales POSITIVOS, no debería poder ser $0

    var concepto by remember { mutableStateOf(viewModel.concepto) }
    var monto by remember { mutableIntStateOf(viewModel.monto) }

    val scope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {

        OutlinedTextField(
            value = concepto,
            onValueChange = { it ->
                concepto = viewModel.cambiarConcepto(it)
            },
            label = {
                Text(text = "Concepto")
            },
            isError = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp)
        )

        viewModel.errorConcepto?.let { mensaje ->
            Text(
                text = mensaje,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }

        OutlinedTextField(
            value = monto.toString(),
            onValueChange = { it ->
                monto = viewModel.cambiarMonto(it)
            },
            label = {
                Text(text = "Monto")
            },
            isError = false,
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        viewModel.errorMonto?.let { mensaje ->
            Text(
                text = mensaje,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }

        Button(
            onClick =
                {
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
                },
            enabled = viewModel.esFormularioValido,
            content = { Text(text = "Guardar", modifier = Modifier.padding(vertical = 18.dp, horizontal = 16.dp)) },
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}