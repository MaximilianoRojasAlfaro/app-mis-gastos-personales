package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.BotonEnviar
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.InputTexto
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.SelectorModoClaroOscuro
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MainViewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MiCuentaViewModel

@Composable
fun MiCuenta(
    mainViewModel: MainViewModel,
    miCuentaViewModel: MiCuentaViewModel
) {
    val uiState by miCuentaViewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        
        Spacer(modifier = Modifier.height(16.dp))

        SelectorModoClaroOscuro(
            modoOscuro = uiState.modoOscuro,
            onClick = {
                miCuentaViewModel.onChangeModo(!uiState.modoOscuro)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        InputTexto(
            valor = uiState.nombreUsuario,
            label = "Nombre de Usuario",
            isError = false, 
            keyboardType = KeyboardType.Text,
            onValueChange = { miCuentaViewModel.onChangeNombreUsuario(it) }
        )

        Spacer(modifier = Modifier.height(8.dp))


        InputTexto(
            valor = if (uiState.limiteGastoDiario == 0) "" else uiState.limiteGastoDiario.toString(),
            label = "Límite Diario ($)",
            isError = false, 
            keyboardType = KeyboardType.Number,
            onValueChange = { miCuentaViewModel.onChangeLimiteGastoDiario(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BotonEnviar(
            isEnabled = true,
            onClick = {
                miCuentaViewModel.guardarConfiguraciones()
                mainViewModel.mostrarMensajeSnackbar("Configuraciones guardadas correctamente")
            }
        )
    }
}