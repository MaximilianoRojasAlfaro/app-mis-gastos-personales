package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenRoutes(
    val ruta: String,
    val titulo: String,
    val iconoActivado: ImageVector,
    val iconoDesactivado: ImageVector
) {

    HOME(
        ruta = "home",
        titulo = "Resumen",
        iconoActivado = Icons.Filled.Home,
        iconoDesactivado = Icons.Outlined.Home
    ),

    NUEVO_GASTO(
        ruta = "nuevo_gasto",
        titulo = "Agregar Gasto",
        iconoActivado = Icons.Filled.AddCircle,
        iconoDesactivado = Icons.Outlined.AddCircle
    ),

    HISTORIAL(
        ruta = "historial",
        titulo = "Historial",
        iconoActivado = Icons.Filled.History,
        iconoDesactivado = Icons.Outlined.History
    )
}