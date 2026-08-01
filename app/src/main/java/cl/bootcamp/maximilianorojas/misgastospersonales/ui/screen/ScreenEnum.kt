package cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen

enum class ScreenEnum(
    val titulo: String,
    val ruta: String
) {

    HOME(
        titulo = "Home",
        ruta = "home"
    ),

    NUEVO_GASTO(
        titulo = "Nuevo Gasto",
        ruta = "nuevoGasto"
    ),

    HISTORIAL(
        titulo = "Historial",
        ruta = "historial"
    )
}