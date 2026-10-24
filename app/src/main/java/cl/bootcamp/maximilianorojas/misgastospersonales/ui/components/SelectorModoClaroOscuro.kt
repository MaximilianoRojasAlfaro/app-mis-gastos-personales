package cl.bootcamp.maximilianorojas.misgastospersonales.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectorModoClaroOscuro(
    modoOscuro: Boolean,
    onClick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
        Text(
            text = "Modo Visual",
            style = MaterialTheme.typography.titleMedium
        )

        IconButton(
            onClick = { onClick() }
        ) {
            Icon(
                imageVector = if (modoOscuro) Icons.Default.LightMode else Icons.Default.DarkMode,
                contentDescription = "Cambiar modo visual",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}