package cl.bootcamp.maximilianorojas.misgastospersonales.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cl.bootcamp.maximilianorojas.misgastospersonales.data.Gasto

@Composable
fun TarjetaResumen(modifier: Modifier, titulo: String, listaGastos: List<Gasto>, tipoTarjeta: TipoTarjetaEnum) {
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