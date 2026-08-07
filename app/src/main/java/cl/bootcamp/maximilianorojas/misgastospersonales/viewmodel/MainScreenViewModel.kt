package cl.bootcamp.maximilianorojas.misgastospersonales.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.bootcamp.maximilianorojas.misgastospersonales.ui.screen.ScreenEnum

class MainScreenViewModel: ViewModel() {

    var actualScreen by mutableStateOf(ScreenEnum.HOME)
        private set

    fun cambiarPantalla(nuevaPantalla: ScreenEnum): ScreenEnum {
        actualScreen = nuevaPantalla
        return actualScreen
    }
}