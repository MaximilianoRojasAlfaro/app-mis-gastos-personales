package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.TarjetaResumen
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.components.TipoTarjetaEnum
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.NuevoGastoViewModel

@Composable
fun Home(
    modifier: Modifier,
    viewModel: NuevoGastoViewModel = viewModel()
){
    val gastoMensual: TipoTarjetaEnum = TipoTarjetaEnum.GASTO_MES_ACTUAL
    val gastoMasAlto: TipoTarjetaEnum = TipoTarjetaEnum.GASTOS_MAS_ALTOS

    val modoHorizontal = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    //Control del modo horizontal para no romper la interfaz al rotar el telefono
    if(!modoHorizontal){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ){

            TarjetaResumen(
                titulo = gastoMensual.titulo,
                listaGastos = viewModel.obtenerTotalesGastosMesesAnteriores(),
                modifier = Modifier.weight(.5f).padding(16.dp),
                tipoTarjeta = gastoMensual
            )

            TarjetaResumen(
                titulo = gastoMasAlto.titulo,
                listaGastos = viewModel.obtenerMayoresGastos(),
                modifier = Modifier.weight(.5f).padding(16.dp),
                tipoTarjeta = gastoMasAlto
            )
        }

    } else {

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()
        ){
            TarjetaResumen(
                titulo = gastoMensual.titulo,
                listaGastos = viewModel.obtenerTotalesGastosMesesAnteriores(),
                modifier = Modifier.weight(.5f).padding(16.dp),
                tipoTarjeta = gastoMensual
            )

            TarjetaResumen(
                titulo = gastoMasAlto.titulo,
                listaGastos = viewModel.obtenerMayoresGastos(),
                modifier = Modifier.weight(.5f).padding(16.dp),
                tipoTarjeta = gastoMasAlto
            )
        }
    }
}