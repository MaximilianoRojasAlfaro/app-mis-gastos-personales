package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.data.Gasto
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.HomeViewModel

@Composable
fun Home(
    modifier: Modifier,
    viewModel: HomeViewModel = viewModel()
){

    //Pantalla de inicio para dar contexto y poder navegar a las demás pantallas
    //Pensar en algo más de utilidad para mostrar aquí
    //Quizás un resumen del historial diario con un total y cantidad de gastos registrados(o esto en pantalla Historial)
    //Quizás el gasto más alto
    //La idea es que en esta pantalla el usuario pueda ver la información que quiere, no tener que navegar a las demás pantallas para obtener un resumen rápido

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ){

        Card(
            modifier = Modifier.weight(.5f).padding(horizontal = 16.dp, vertical = 16.dp)
        ){
            //TODO: Posiblemente cambiar a futuro a una LazyColumn ya que quiero mostrar aprox unos 10 días con sus totales, o hasta de todo el mes
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                //TODO: Modular Text[TituloCardResumen] a paquete *components*
                Text(
                    text = "Total Mensual de Gastos: $50000",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 24.dp)
                )

                //TODO: Modular Row(GastoDelDia) a paquete *components*
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "05/08/2026", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).weight(.5f))

                    Text(text = "$35000", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).weight(.5f))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "05/08/2026", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().weight(.5f))
                    Text(text = "$5000", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().weight(.5f))

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "04/08/2026", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().weight(.5f))
                    Text(text = "$15000", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().weight(.5f))
                }
            }
        }

        Card(
            modifier = Modifier.weight(.5f).padding(horizontal = 16.dp, vertical = 16.dp)
        ){

            //TODO: Posiblemente cambiar a futuro a una LazyColumn ya que quiero mostrar aprox unos 10 conceptos con el gasto más alto (ordenados de mayor a menor)
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                //TODO: Modular Text[TituloCardResumen] a paquete *components*
                Text(
                    text = "Gastos más altos:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 24.dp)
                )

                //TODO: Modular Row(GastoMasAltoDelDia) a paquete *components*
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Concepto:", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().weight(.5f))
                    Text(text = "$15000", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().weight(.5f))
                }
            }
        }
    }
}