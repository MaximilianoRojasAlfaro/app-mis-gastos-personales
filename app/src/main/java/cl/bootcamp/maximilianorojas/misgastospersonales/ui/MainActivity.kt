package cl.bootcamp.maximilianorojas.misgastospersonales.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen.Historial
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen.Home
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen.NuevoGasto
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen.ScreenEnum
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.theme.MisGastosPersonalesTheme
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = HomeViewModel()
        setContent {
            MisGastosPersonalesTheme {
                Scaffold(

                    topBar = {

                        TopAppBar(
                            title = {
                                //Titulo superior de la pantalla(debería usar el del Enum)
                            }
                        )
                    },

                    bottomBar = {

                        BottomAppBar {

                            Row() {
                                //Aquí van los botones inferiores de navegación(con su icono correspondiente)
                            }
                        }
                    },

                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    val modifier = Modifier.padding(innerPadding)

                    when (viewModel.actualScreen) {

                        ScreenEnum.HOME -> Home(modifier = modifier)
                        ScreenEnum.NUEVO_GASTO -> NuevoGasto(modifier = modifier)
                        ScreenEnum.HISTORIAL -> Historial(modifier = modifier)
                    }
                }
            }
        }
    }
}
