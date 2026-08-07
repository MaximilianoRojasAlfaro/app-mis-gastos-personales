package cl.bootcamp.maximilianorojas.misgastospersonales.data

data class Gasto(
    var concepto: String,
    val monto: Double,
    val fecha: String,
    val hora: String
)
