package cl.bootcamp.maximilianorojas.misgastospersonales.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.maximilianorojas.misgastospersonales.data.Gasto

@Composable
fun TarjetaResumen(modifier: Modifier, titulo: String, listaGastos: List<Gasto>, tipoTarjeta: TipoTarjetaEnum) {
    Card(
        modifier = modifier
    ){
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
            content = {

                item {
                    Text(
                        text = titulo,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 24.dp)
                    )
                }

                items(listaGastos) { gasto ->

                    when(tipoTarjeta) {

                        TipoTarjetaEnum.GASTO_MES_ACTUAL -> ItemResumen(nombreItem = gasto.fecha, gasto = gasto)

                        TipoTarjetaEnum.GASTOS_MAS_ALTOS -> ItemResumen(nombreItem = gasto.concepto,gasto = gasto)
                    }
                }
            }
        )
    }
}