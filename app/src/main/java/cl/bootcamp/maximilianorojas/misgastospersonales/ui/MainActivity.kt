package cl.bootcamp.maximilianorojas.misgastospersonales.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cl.bootcamp.maximilianorojas.misgastospersonales.data.repository.GastoRepository
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screens.MisGastosPersonalesApp
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.theme.MisGastosPersonalesTheme
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MainViewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.NuevoGastoViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val gastoRepository = GastoRepository(this)
        
        val mainViewModel = MainViewModel(gastoRepository)
        val nuevoGastoViewModel = NuevoGastoViewModel(gastoRepository)

        setContent {
            MisGastosPersonalesTheme {
                MisGastosPersonalesApp(
                    mainViewModel = mainViewModel,
                    nuevoGastoViewModel = nuevoGastoViewModel
                )
            }
        }
    }
}

