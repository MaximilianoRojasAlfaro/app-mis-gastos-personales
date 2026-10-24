package cl.bootcamp.maximilianorojas.misgastospersonales.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun InputTexto(
    valor: String,
    label: String,
    isError: Boolean,
    mensajeError: String? = null,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        isError = isError,
        supportingText = {
            if(isError && mensajeError != null) {
                Text(text = mensajeError)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    )
}