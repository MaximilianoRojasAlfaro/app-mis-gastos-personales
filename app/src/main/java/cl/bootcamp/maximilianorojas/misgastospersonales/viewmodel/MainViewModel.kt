package cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.maximilianorojas.misgastospersonales.data.modelo.Gasto
import cl.bootcamp.maximilianorojas.misgastospersonales.data.modelo.ResumenMes
import cl.bootcamp.maximilianorojas.misgastospersonales.data.repository.GastoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class MainUiState(
    val listaGastos: List<Gasto> = emptyList(),
)

class MainViewModel(
    private val gastoRepository: GastoRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    private val _mensajeSnackbar = MutableStateFlow<String?>(null)
    val mensajeSnackbar = _mensajeSnackbar.asStateFlow()

    init {

        viewModelScope.launch {

            gastoRepository.getAllGastos().collect { gastosBD ->

                _uiState.update { estadoActual ->
                    estadoActual.copy(listaGastos = gastosBD)
                }
            }
        }
    }



    fun agregarGasto(gasto: Gasto): Long {

        var idAgregado: Long = 0

        viewModelScope.launch {
            idAgregado = gastoRepository.addGasto(gasto)
        }

        return idAgregado

    }

    fun eliminarGasto(gasto: Gasto) {

        viewModelScope.launch {
            gastoRepository.deleteGasto(gasto)
        }
    }


    fun obtenerGastosMasAltosDelMes(mes: Int): List<Gasto> {

        return _uiState.value.listaGastos
            .filter { it.fecha.substring(5, 7).toInt() == mes }
            .sortedByDescending { it.monto }
            .take(5)
    }

    fun obtenerGastosPorMes(): List<ResumenMes> {

        val agrupadosPorMes: Map<String, List<Gasto>> = _uiState.value.listaGastos.groupBy { gasto ->
            gasto.fecha.substring(0, 7)
        }

        val resultado = agrupadosPorMes.map { (mes, gastosDelMes) ->
            ResumenMes(
                mes = mes,
                totalMonto = gastosDelMes.sumOf { it.monto }
            )
        }

        return resultado.sortedByDescending { it.mes }
    }

    fun obtenerTotalGastos(): Int {

        return _uiState.value.listaGastos
            .sumOf { it.monto }
    }

    fun mostrarMensajeSnackbar(mensaje: String) {
        _mensajeSnackbar.value = mensaje
    }

    fun limpiarMensajeSnackbar() {
        _mensajeSnackbar.value = null
    }


    //---- Formateo y Fechas ----

    //Limpia el concepto para no guardar datos basura
    fun formatearConcepto(concepto: String): String {

        val conceptoFormateado = concepto.trim().replaceFirstChar { it.uppercase() }

        return conceptoFormateado
    }

    //Se asume que el formato es el recortado: "yyyy-MM"
    fun obtenerNombreMes(fecha: String): String {

        val mesNumero = fecha.substring(startIndex = 5)
        val mesString = when(mesNumero) {

            "01" -> "Enero"
            "02" -> "Febrero"
            "03" -> "Marzo"
            "04" -> "Abril"
            "05" -> "Mayo"
            "06" -> "Junio"
            "07" -> "Julio"
            "08" -> "Agosto"
            "09" -> "Septiembre"
            "10" -> "Octubre"
            "11" -> "Noviembre"
            "12" -> "Diciembre"
            else -> "Mes Erróneo"
        }

        return mesString
    }

    //Formato para mostrar fecha como dd/mm/yyyy (como la fecha viene)
    fun formatearFechaParaVista(fecha: String): String {

        val partes = fecha.split("-")

        if (partes.size == 3) {

            val ano = partes[0]
            val mes = partes[1]
            val dia = partes[2]

            return "$dia/$mes/$ano"
        }

        return fecha
    }
}