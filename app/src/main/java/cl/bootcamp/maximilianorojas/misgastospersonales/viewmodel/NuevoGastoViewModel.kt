package cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel

import androidx.lifecycle.ViewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.data.repository.GastoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class NuevoGastoUiState(
    val concepto: String = "",
    val monto: String = "", // para no mostrar campo con valor 0
    val errorConcepto: String? = null,
    val errorMonto: String? = null,
    val esFormularioValido: Boolean = false
)

class NuevoGastoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NuevoGastoUiState())
    val uiState = _uiState.asStateFlow()

    fun onChangeConcepto(nuevoConcepto: String) {

        _uiState.update { estadoActual ->

            val error = if (nuevoConcepto.isBlank()) "El concepto no puede estar vacío" else null

            estadoActual.copy(
                concepto = nuevoConcepto,
                errorConcepto = error,
                esFormularioValido = validarFormulario(
                    concepto = nuevoConcepto,
                    errorConcepto = error,
                    monto = estadoActual.monto,
                    errorMonto = estadoActual.errorMonto
                )
            )
        }
    }

    fun onChangeMonto(nuevoMonto: String) {

        _uiState.update { estadoActual ->

            val error = if (nuevoMonto.isBlank() || nuevoMonto.toIntOrNull() == null) "Ingrese un monto válido" else null

            estadoActual.copy(
                monto = nuevoMonto,
                errorMonto = error,
                esFormularioValido = validarFormulario(
                    concepto = estadoActual.concepto,
                    errorConcepto = estadoActual.errorConcepto,
                    monto = nuevoMonto,
                    errorMonto = error
                )
            )
        }
    }

    private fun validarFormulario(
        concepto: String,
        errorConcepto: String?,
        monto: String,
        errorMonto: String?
    ): Boolean {

        val conceptoValido = concepto.isNotBlank() && errorConcepto == null
        val montoValido = monto.isNotBlank() && errorMonto == null

        return conceptoValido && montoValido
    }

    fun limpiarFormulario() {
        _uiState.value = NuevoGastoUiState()
    }
}