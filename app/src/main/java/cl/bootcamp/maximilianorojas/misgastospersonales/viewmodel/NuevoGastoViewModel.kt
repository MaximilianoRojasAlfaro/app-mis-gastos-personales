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

    //Se maneja como String? para que no esté el 0 al principio del campo y que no se rompa cuando el monto supera el límite de los Ints
    var monto by mutableStateOf<String?>(null)
        private set

    var errorConcepto: String? by mutableStateOf(null)
        private set

    var errorMonto: String? by mutableStateOf(null)
        private set

    val esFormularioValido: Boolean
        get() = errorConcepto == null && errorMonto == null && monto != null // Se agrega validacion con monto para evitar que pueda guardar con [monto = 0] o [monto = null]


    //Limpia el concepto para no guardar datos basura
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
                    monto = this.monto?.toInt()?.toDouble() ?: 0.0,
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

        errorConcepto = when {

            concepto.isEmpty() || concepto.isBlank() -> "El concepto no puede estar vacío"
            concepto.length > 40 -> "El concepto no puede tener más de 40 caracteres"
            else -> null
        }

        return this.concepto
    }

    fun eliminarConcepto(): String {
        this.concepto = ""
        return this.concepto
    }

    fun cambiarMonto(monto: String): String? {

        this.monto = monto

        val numero = monto.toLongOrNull()

        errorMonto = when {

            numero == null -> "Ingresa un número válido"
            numero > Int.MAX_VALUE -> "El monto no puede superar 2.147.483.647"
            numero <= 0 -> "El monto no puede ser menor a 0"
            else -> null
        }

        return this.monto
    }

    fun eliminarMonto(): String? {
        this.monto = null
        return this.monto
    }

    fun obtenerMontoTotal(): Int {

        return listaGastos.sumOf { it.monto }.toInt()
    }

    //Obtiene la lista de los 5 mayores gastos del mes actual
    fun obtenerMayoresGastos(): List<Gasto> {

        val formatoMesActual = SimpleDateFormat("yyyy-MM", Locale.getDefault())
        val mesActual = formatoMesActual.format(Date())

        return listaGastos
            .filter { it.fecha.startsWith(mesActual) }
            .sortedByDescending { it.monto }
            .take(5)
    }

    //Obtiene la lista de los últimos 5 meses(contando el actual) con su monto total
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

    //Formato para mostrar fecha como dd/mm/yyyy
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