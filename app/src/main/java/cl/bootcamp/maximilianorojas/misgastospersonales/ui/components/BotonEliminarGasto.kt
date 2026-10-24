package cl.bootcamp.maximilianorojas.misgastospersonales.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BotonEliminarGasto(modifier: Modifier, onClick:() -> Unit) {

    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        ),
        onClick = { onClick() },
        content = {
            Icon(
                Icons.Filled.Clear,
                contentDescription = "Eliminar gasto",
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
            )
        }
    )
}