package cl.bootcamp.maximilianorojas.misgastospersonales.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BotonEnviar(isEnabled: Boolean, onClick: () -> Unit) {

    Button(
        onClick = { onClick() },
        enabled = isEnabled,
        content = { Text(text = "Guardar", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(vertical = 18.dp, horizontal = 40.dp)) },
        modifier = Modifier.padding(vertical = 16.dp)
    )
}