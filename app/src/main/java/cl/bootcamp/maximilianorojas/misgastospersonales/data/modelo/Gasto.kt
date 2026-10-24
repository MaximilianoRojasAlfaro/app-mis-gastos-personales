package cl.bootcamp.maximilianorojas.misgastospersonales.data.modelo

data class Gasto(
    var id: Int,
    var concepto: String,
    val monto: Int,
    val fecha: String
)
