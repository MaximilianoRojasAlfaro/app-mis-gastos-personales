package cl.bootcamp.maximilianorojas.misgastospersonales.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GastoDAO {

    @Insert
    suspend fun addGasto(gastoEntity: GastoEntity): Long //Devuelve el id del gasto creado

    @Query("SELECT * FROM gasto ORDER BY fecha DESC")
    fun getAllGastos(): Flow<List<GastoEntity>>

    @Delete
    suspend fun deleteGasto(gastoEntity: GastoEntity)
}