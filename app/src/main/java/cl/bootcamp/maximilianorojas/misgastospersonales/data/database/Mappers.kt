package cl.bootcamp.maximilianorojas.misgastospersonales.data.database

import cl.bootcamp.maximilianorojas.misgastospersonales.data.modelo.Gasto

fun GastoEntity.toGasto(): Gasto {
    return Gasto(
        id = this.id,
        concepto = this.concepto,
        monto = this.monto,
        fecha = this.fecha
    )
}

fun Gasto.toGastoEntity(): GastoEntity {
    return GastoEntity(
        id = this.id,
        concepto = this.concepto,
        monto = this.monto,
        fecha = this.fecha
    )
}