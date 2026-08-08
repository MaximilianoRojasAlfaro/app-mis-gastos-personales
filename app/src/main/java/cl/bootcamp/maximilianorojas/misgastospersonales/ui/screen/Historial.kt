package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.NuevoGastoViewModel

@Composable
fun Historial(
    modifier: Modifier,
    viewModel: NuevoGastoViewModel = viewModel()
) {
    //Se encarga de mostrar todo el historial de gastos agregados
    //Tiene que ser una LazyColumn y dentro cards para mostrar el concepto - monto del gasto

    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
        content = {

            items(viewModel.listaGastos) { gasto ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp, horizontal = 16.dp)
                    ) {

                        Column(
                            modifier = Modifier.weight(.5f)
                        ){
                            Text(
                                text = gasto.concepto,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )

                            Text(
                                text = "${viewModel.formatearFechaParaVista(gasto.fecha)} - ${gasto.hora}",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        Text(
                            text = "$${gasto.monto.toInt()}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(.5f)
                        )

                    }
                }
            }
        }
    )



}