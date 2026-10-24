package cl.bootcamp.maximilianorojas.misgastospersonales.data.repository

import android.content.Context
import android.util.Log
import cl.bootcamp.maximilianorojas.misgastospersonales.data.database.AppDatabase
import cl.bootcamp.maximilianorojas.misgastospersonales.data.database.toGasto
import cl.bootcamp.maximilianorojas.misgastospersonales.data.database.toGastoEntity
import cl.bootcamp.maximilianorojas.misgastospersonales.data.modelo.Gasto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GastoRepository(
    context: Context
) {

    private val database = AppDatabase.getInstance(context = context)
    private val gastoDAO = database.gastoDAO()

    fun getAllGastos(): Flow<List<Gasto>> {

        Log.d("[GET ALL GASTOS]", "Obteniendo lista de gastos")
        return gastoDAO.getAllGastos().map { listaEntities ->

            listaEntities.map { entity ->
                entity.toGasto()
            }
        }
    }

    suspend fun addGasto(gasto: Gasto): Long {

        Log.d("[ADD GASTO]", "Agregando gasto: $gasto")
        return gastoDAO.addGasto(gastoEntity = gasto.toGastoEntity())
    }

    suspend fun deleteGasto(gasto: Gasto) {
        Log.d("[DELETE GASTO]", "Eliminando gasto: $gasto")
        gastoDAO.deleteGasto(gastoEntity = gasto.toGastoEntity())
    }
}