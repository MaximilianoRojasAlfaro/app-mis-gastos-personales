package cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.data.Gasto
import java.util.Date

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

    val esFormularioValido: Boolean
        get() = errorConcepto == null && errorMonto == null

    fun formatearConcepto(concepto: String): String {

        val conceptoFormateado = concepto.trim().replaceFirstChar { it.uppercase() }

        return conceptoFormateado
    }

    fun agregarGasto() {

        val date = Date()
        val fecha = date.toString().substring(0, 10)
        val hora = date.toString().substring(11, 16)

        val conceptoFormateado = formatearConcepto(this.concepto)

        listaGastos.add(Gasto(
            concepto = conceptoFormateado,
            monto = this.monto.toDouble(),
            fecha = fecha,
            hora = hora
        ))
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

}