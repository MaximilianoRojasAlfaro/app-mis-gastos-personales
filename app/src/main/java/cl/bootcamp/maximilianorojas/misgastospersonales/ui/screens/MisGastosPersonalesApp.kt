package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.BarraInferior
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.BarraSuperior
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MainViewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MiCuentaViewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.NuevoGastoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MisGastosPersonalesApp(
    mainViewModel: MainViewModel,
    nuevoGastoViewModel: NuevoGastoViewModel,
    miCuentaViewModel: MiCuentaViewModel
) {
    val miCuentauiState by miCuentaViewModel.uiState.collectAsState()

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ScreenRoutes.HOME.ruta
    val pantallaActual = remember(currentRoute) {
        ScreenRoutes.entries.find { it.ruta == currentRoute } ?: ScreenRoutes.HOME
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val mensajeSnackbar by mainViewModel.mensajeSnackbar.collectAsStateWithLifecycle()

    LaunchedEffect(mensajeSnackbar) {

        mensajeSnackbar?.let { texto ->

            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                message = texto,
                duration = SnackbarDuration.Short
            )

            mainViewModel.limpiarMensajeSnackbar()
        }
    }


    Scaffold(

        topBar = {

            BarraSuperior(
                titulo = if(pantallaActual == ScreenRoutes.HOME) "Bienvenido ${miCuentauiState.nombreUsuario}" else pantallaActual.titulo
            )
        },

        bottomBar = {

            BarraInferior(
                rutaActual = currentRoute,
                onNavigateTo = { ruta ->

                    navController.navigate(ruta)
                }
            )
        },

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            composable(ScreenRoutes.HOME.ruta) {
                Home(
                    mainViewModel = mainViewModel
                )
            }

            composable(ScreenRoutes.NUEVO_GASTO.ruta) {
                NuevoGasto(
                    mainViewModel = mainViewModel,
                    nuevoGastoViewModel = nuevoGastoViewModel
                )
            }

            composable(ScreenRoutes.HISTORIAL.ruta) {
                Historial(
                    mainViewModel = mainViewModel
                )
            }

            composable(ScreenRoutes.MI_CUENTA.ruta) {
                MiCuenta(
                    mainViewModel = mainViewModel,
                    miCuentaViewModel = miCuentaViewModel
                )
            }
        }
    }
}