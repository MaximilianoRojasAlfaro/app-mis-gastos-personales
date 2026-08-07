package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenEnum(
    val titulo: String,
    val route: String,
    val icono: ImageVector
) {

    HOME(
        titulo = "Resumen",
        route = "home",
        icono = Icons.Filled.Home
    ),

    NUEVO_GASTO(
        titulo = "Nuevo Gasto",
        route = "nuevoGasto",
        icono = Icons.Filled.AddCircle
    ),

    HISTORIAL(
        titulo = "Historial",
        route = "historial",
        icono = Icons.Filled.History
    )
}