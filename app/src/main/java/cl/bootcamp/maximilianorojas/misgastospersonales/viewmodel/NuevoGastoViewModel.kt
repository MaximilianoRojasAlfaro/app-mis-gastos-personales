package cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.data.Gasto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NuevoGastoViewModel: ViewModel() {

    val listaGastos = mutableStateListOf<Gasto>()



    var concepto by mutableStateOf("")
        private set

    var monto by mutableIntStateOf(0)
        private set

    var errorConcepto: String? by mutableStateOf(null)
        private set

    var errorMonto: String? by mutableStateOf(null)
        private set

    //TODO: Agregar al enabled del botón agregar gasto
    val esFormularioValido: Boolean
        get() = errorConcepto == null && errorMonto == null

    fun formatearConcepto(concepto: String): String {

        val conceptoFormateado = concepto.trim().replaceFirstChar { it.uppercase() }

        return conceptoFormateado
    }

    fun agregarGasto() {

        val date = Date()
        val formatoFecha = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatoHora = SimpleDateFormat("HH:mm", Locale.getDefault())
        val fecha = formatoFecha.format(date)
        val hora = formatoHora.format(date)

        val conceptoFormateado = formatearConcepto(this.concepto)

        listaGastos.add(
            index = 0,
            element =
                Gasto(
                    concepto = conceptoFormateado,
                    monto = this.monto.toDouble(),
                    fecha = fecha,
                    hora = hora
                )
        )
    }

    fun eliminarGasto(gasto: Gasto) {

        listaGastos.remove(gasto)
    }

    fun cambiarConcepto(concepto: String): String {

        this.concepto = concepto

        errorConcepto =
            if(concepto.isEmpty() || concepto.isBlank())
                "El concepto no puede estar vacío"
            else if(concepto.length > 40)
                "El concepto no puede tener más de 40 caracteres"
            else null

        return this.concepto
    }

    fun eliminarConcepto(): String {
        this.concepto = ""
        return this.concepto
    }

    fun cambiarMonto(monto: String): Int {

        this.monto = monto.toIntOrNull() ?: 0

        errorMonto =
            if(monto.isEmpty() || monto.isBlank())
                "El monto no puede estar vacío"
            else if(monto.toIntOrNull() == null)
                "El monto debe ser un número"
            else if(this.monto <= 0)
                "El monto debe ser mayor a 0"
            else null

        return this.monto
    }

    fun eliminarMonto(): Int {
        this.monto = 0
        return this.monto
    }

    fun obtenerMontoTotal(): Int {

        return listaGastos.sumOf { it.monto }.toInt()
    }

    fun obtenerMayoresGastos(): List<Gasto> {

        val formatoMesActual = SimpleDateFormat("yyyy-MM", Locale.getDefault())
        val mesActual = formatoMesActual.format(Date())

        return listaGastos
            .filter { it.fecha.startsWith(mesActual) }
            .sortedByDescending { it.monto }
            .take(5)
    }

    fun obtenerTotalesGastosMesesAnteriores(): List<Gasto> {

        return listaGastos
            .groupBy { it.fecha.substring(0, 7) }
            .map { (mes, gastosDelMes) ->
                Gasto(
                    concepto = "",
                    monto = gastosDelMes.sumOf { it.monto },
                    fecha = obtenerNombreMes(mes),
                    hora = ""
                )
            }
            .take(5)
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