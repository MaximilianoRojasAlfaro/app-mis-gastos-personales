package cl.bootcamp.maximilianorojas.misgastospersonales.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel.MiCuentaUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "configuraciones_usuario")

class ConfiguracionesRepository(
    private val dataStore: DataStore<Preferences>
) {

    private companion object {

        val MODO_OSCURO = booleanPreferencesKey("modo_oscuro")
        val NOMBRE_USUARIO = stringPreferencesKey("nombre_usuario")
        val LIMITE_DIARIO = intPreferencesKey("limite_diario")
    }

    val configuracionesFlow: Flow<MiCuentaUiState> = dataStore.data.map { preferences ->

        MiCuentaUiState(
            isLoading = false,
            modoOscuro = preferences[MODO_OSCURO] ?: false,
            nombreUsuario = preferences[NOMBRE_USUARIO] ?: "",
            limiteGastoDiario = preferences[LIMITE_DIARIO] ?: 0
        )
    }

    suspend fun guardarModoOscuro(esModoOscuro: Boolean) {

        dataStore.edit { preferences ->
            preferences[MODO_OSCURO] = esModoOscuro
        }
    }

    suspend fun guardarNombreUsuario(nombre: String) {

        dataStore.edit { preferences ->
            preferences[NOMBRE_USUARIO] = nombre
        }
    }

    suspend fun guardarLimiteDiario(limite: Int) {
        dataStore.edit { preferences ->
            preferences[LIMITE_DIARIO] = limite
        }
    }
}