package dev.firecrown.scriptest.data.repositories

import dev.firecrown.scriptest.data.entities.ResultEntity
import kotlinx.coroutines.flow.MutableStateFlow

internal object BlockRepository {
    val title = MutableStateFlow<String>("Title")
    val text = MutableStateFlow<String>("Text")
    val textField = MutableStateFlow<String?>(null)
    val pointer = MutableStateFlow<List<Float>?>(null)
    val options = MutableStateFlow<List<String>?>(null)
    val option = MutableStateFlow<String?>(null)
    val snapshot = MutableStateFlow<String?>(null)
    val isBlockExecuting = MutableStateFlow(false)
    val scriptName = MutableStateFlow<String>("")
    val resultList = MutableStateFlow<ArrayList<ResultEntity>>(arrayListOf())
    val isScriptRunning = MutableStateFlow<Boolean?>(null)
    val dialogOverlayPosition = MutableStateFlow("Center")
    val textFiledTypedText = MutableStateFlow<String>("")

    fun setDefaultValues() {
        title.value = "Text"
        text.value = "Title"
        textField.value = null
        pointer.value = null
        options.value = null
        option.value = null
        snapshot.value = null
        dialogOverlayPosition.value = "Center"
        textFiledTypedText.value = ""
    }
}