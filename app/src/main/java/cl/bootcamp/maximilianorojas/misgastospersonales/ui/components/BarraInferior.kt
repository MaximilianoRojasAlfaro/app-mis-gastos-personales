package cl.bootcamp.maximilianorojas.misgastospersonales.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screens.ScreenRoutes

@Composable
fun BarraInferior(
    rutaActual: String,
    onNavigateTo: (String) -> Unit
) {

    BottomAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {

        NavigationBar(
        ) {

            ScreenRoutes.entries.forEach { pantalla ->

                val estaSeleccionada = rutaActual == pantalla.ruta

                NavigationBarItem(
                    selected = estaSeleccionada,
                    label = { Text(text = pantalla.titulo, style = MaterialTheme.typography.labelSmall) },
                    colors = NavigationBarItemColors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        selectedTextColor = MaterialTheme.colorScheme.onSurface,
                        selectedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledIconColor = MaterialTheme.colorScheme.onSurface,
                        disabledTextColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    icon = {
                        Icon(
                            imageVector = if (estaSeleccionada) pantalla.iconoActivado else pantalla.iconoDesactivado,
                            contentDescription = pantalla.titulo
                        )
                    },
                    onClick = {
                        if (!estaSeleccionada) {
                            onNavigateTo(pantalla.ruta)
                        }
                    }
                )
            }
        }
    }
}