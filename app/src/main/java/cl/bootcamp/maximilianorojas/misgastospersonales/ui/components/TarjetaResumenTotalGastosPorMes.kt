package cl.bootcamp.maximilianorojas.misgastospersonales.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cl.bootcamp.maximilianorojas.misgastospersonales.data.modelo.ResumenMes

@Composable
fun TarjetaResumenTotalGastosPorMes(modifier: Modifier, titulo: String, listaResumenMes: List<ResumenMes>) {

    Card(
        modifier = modifier
    ){
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            content = {

                item {
                    TituloTarjeta(
                        texto = titulo
                    )
                }

                items(listaResumenMes) { resumen ->

                    ItemResumen(
                        nombreItem = resumen.mes,
                        monto = resumen.totalMonto
                    )
                }
            }
        )
    }
}