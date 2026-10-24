package cl.bootcamp.maximilianorojas.misgastospersonales.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cl.bootcamp.maximilianorojas.misgastospersonales.data.repository.ConfiguracionesRepository
import cl.bootcamp.maximilianorojas.misgastospersonales.data.repository.GastoRepository
import cl.bootcamp.maximilianorojas.misgastospersonales.data.repository.dataStore
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screens.MisGastosPersonalesApp
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.theme.MisGastosPersonalesTheme
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MainViewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MiCuentaViewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.NuevoGastoViewModel

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val gastoRepository = GastoRepository(this)
        val configuracionesRepository = ConfiguracionesRepository(applicationContext.dataStore)
        
        val mainViewModel = MainViewModel(gastoRepository)
        val nuevoGastoViewModel = NuevoGastoViewModel()
        val miCuentaViewModel = MiCuentaViewModel(configuracionesRepository)

        setContent {

            val configuracionState by miCuentaViewModel.uiState.collectAsState()

            if (!configuracionState.isLoading) {
                MisGastosPersonalesTheme(darkTheme = configuracionState.modoOscuro) {
                    MisGastosPersonalesApp(
                        mainViewModel = mainViewModel,
                        nuevoGastoViewModel = nuevoGastoViewModel,
                        miCuentaViewModel = miCuentaViewModel
                    )
                }
            }
        }
    }
}

