package cl.bootcamp.maximilianorojas.misgastospersonales.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(
    titulo: String
) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    )
}