package cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.maximilianorojas.misgastospersonales.data.repository.ConfiguracionesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MiCuentaUiState(
    val isLoading: Boolean = true,
    val modoOscuro: Boolean = false,
    val nombreUsuario: String = "",
    val limiteGastoDiario: Int = 0 //Encontrar uso
)

class MiCuentaViewModel(
    private val configuracionesRepository: ConfiguracionesRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(MiCuentaUiState())
    val uiState = _uiState.asStateFlow()

    init {

        viewModelScope.launch {
            configuracionesRepository.configuracionesFlow.collect { prefsGuardadas ->
                _uiState.update { estadoActual ->
                    estadoActual.copy(
                        isLoading = prefsGuardadas.isLoading,
                        modoOscuro = prefsGuardadas.modoOscuro,
                        nombreUsuario = if (estadoActual.isLoading) prefsGuardadas.nombreUsuario else estadoActual.nombreUsuario,
                        limiteGastoDiario = if (estadoActual.isLoading) prefsGuardadas.limiteGastoDiario else estadoActual.limiteGastoDiario
                    )
                }
            }
        }
    }

    fun onChangeModo(esModoOscuro: Boolean) {
        viewModelScope.launch {
            configuracionesRepository.guardarModoOscuro(esModoOscuro)
        }
    }

    fun onChangeNombreUsuario(nuevoNombre: String) {
        _uiState.update { it.copy(nombreUsuario = nuevoNombre) }
    }

    fun onChangeLimiteGastoDiario(nuevoLimite: String) {
        val limite = nuevoLimite.toIntOrNull() ?: 0
        _uiState.update { it.copy(limiteGastoDiario = limite) }
    }

    fun guardarConfiguraciones() {
        viewModelScope.launch {
            configuracionesRepository.guardarNombreUsuario(_uiState.value.nombreUsuario)
            configuracionesRepository.guardarLimiteDiario(_uiState.value.limiteGastoDiario)
        }
    }
}