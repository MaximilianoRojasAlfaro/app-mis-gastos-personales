package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import cl.bootcamp.maximilianorojas.misgastospersonales.data.Gasto

enum class ScreenEnum(
    val titulo: String,
    val icono: ImageVector
) {

    HOME(
        titulo = "Resumen",
        icono = Icons.Filled.Home
    ),

    NUEVO_GASTO(
        titulo = "Nuevo Gasto",
        icono = Icons.Filled.AddCircle
    ),

    HISTORIAL(
        titulo = "Historial",
        icono = Icons.Filled.History
    )
}