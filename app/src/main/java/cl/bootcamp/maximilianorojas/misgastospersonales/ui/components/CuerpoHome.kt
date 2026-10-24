package cl.bootcamp.maximilianorojas.misgastospersonales.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.bootcamp.maximilianorojas.misgastospersonales.data.modelo.Gasto
import cl.bootcamp.maximilianorojas.misgastospersonales.data.modelo.ResumenMes

@Composable
fun CuerpoHome(
    tituloPrimeraTarjeta: String,
    tituloSegundaTarjeta: String,
    listaResumenMes: List<ResumenMes>,
    listaGastosDelMes: List<Gasto>,
    modifier: Modifier
) {

    TarjetaResumenTotalGastosPorMes(
        titulo = tituloPrimeraTarjeta,
        listaResumenMes = listaResumenMes,
        modifier = modifier,
    )

    TarjetaResumenGastosMasAltosDelMes(
        titulo = tituloSegundaTarjeta,
        listaGastos = listaGastosDelMes,
        modifier = modifier,
    )
}