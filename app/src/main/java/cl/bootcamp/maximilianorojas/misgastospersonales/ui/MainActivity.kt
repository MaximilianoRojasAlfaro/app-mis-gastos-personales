package cl.bootcamp.maximilianorojas.misgastospersonales.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen.Historial
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen.Home
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen.NuevoGasto
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen.ScreenEnum
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.theme.MisGastosPersonalesTheme
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MainScreenViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = MainScreenViewModel()
        setContent {
            MisGastosPersonalesTheme {
                MainScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(screenViewModel: MainScreenViewModel = viewModel()) {

    var actualScreen by remember { mutableStateOf(screenViewModel.actualScreen) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = actualScreen.titulo,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },

        bottomBar = {

            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {

                NavigationBar() {

                    val home = ScreenEnum.HOME
                    val nuevoGasto = ScreenEnum.NUEVO_GASTO
                    val historial = ScreenEnum.HISTORIAL

                    NavigationBarItem(
                        selected = actualScreen == home,
                        onClick = { actualScreen = screenViewModel.cambiarPantalla(home) },
                        icon = { Icon(home.icono, contentDescription = home.titulo) },
                        label = { Text(home.titulo) }
                    )

                    NavigationBarItem(
                        selected = actualScreen == nuevoGasto,
                        onClick = { actualScreen = screenViewModel.cambiarPantalla(nuevoGasto) },
                        icon = { Icon(nuevoGasto.icono, contentDescription = nuevoGasto.titulo) },
                        label = { Text(ScreenEnum.NUEVO_GASTO.titulo) }
                    )

                    NavigationBarItem(
                        selected = actualScreen == historial,
                        onClick = { actualScreen = screenViewModel.cambiarPantalla(historial) },
                        icon = { Icon(historial.icono, contentDescription = historial.titulo) },
                        label = { Text(historial.titulo) }
                    )
                }
            }
        },

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        val modifier = Modifier.padding(innerPadding)

        when (actualScreen) {

            ScreenEnum.HOME -> Home(modifier = modifier)
            ScreenEnum.NUEVO_GASTO -> NuevoGasto(modifier = modifier, snackbarHostState)
            ScreenEnum.HISTORIAL -> Historial(modifier = modifier, snackbarHostState)
        }
    }
}
