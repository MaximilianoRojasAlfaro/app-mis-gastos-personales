package cl.bootcamp.maximilianorojas.misgastospersonales.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gasto")
data class GastoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val concepto: String,
    val monto: Int,
    val fecha: String
)
