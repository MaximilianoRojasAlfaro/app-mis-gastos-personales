package cl.bootcamp.maximilianorojas.misgastospersonales.ui.components

enum class TipoTarjetaEnum(
    val titulo: String
) {

    GASTO_MES_ACTUAL(
        titulo = "Gastos totales por mes:"
    ),

    GASTOS_MAS_ALTOS(
        titulo = "Gastos más altos del mes:"
    )
}