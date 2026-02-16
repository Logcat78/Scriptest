package dev.firecrown.scriptest.core

import android.content.Context
import android.util.Log
import dev.firecrown.scriptest.data.entities.ResultEntity
import dev.firecrown.scriptest.data.entities.BlockEntity
import dev.firecrown.scriptest.data.entities.Output
import dev.firecrown.scriptest.data.media.OutputScript
import dev.firecrown.scriptest.data.repositories.BlockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

internal class Script(
    val context: Context
) {

    private suspend fun processBlock(blockEntity: BlockEntity): ResultEntity {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        BlockRepository.apply {
            isBlockExecuting.value = true

            title.value = blockEntity.title
            text.value = blockEntity.text
            pointer.value = blockEntity.pointer
            options.value = blockEntity.options
            dialogOverlayPosition.value = blockEntity.dialogOverlayPosition
            textField.value = blockEntity.textField

            if(blockEntity.snapshot) snapshot.value = "snapshot"

            isBlockExecuting.first { !it }

            return ResultEntity(
                textField = textFiledTypedText.value,
                snapshot = snapshot.value,
                option = option.value,
                exceptionMessage = null,
                timestamp = sdf.format(Date())
            )
        }
    }

    fun execute(
        scriptText: List<BlockEntity>,
        output: Output
    ){
        CoroutineScope(Dispatchers.IO).apply {
            val script = async {
                //val filtered = scriptText.filter { it != null }
                scriptText.forEach { scriptEntity ->
                    val processBlock = processBlock(scriptEntity)
                    BlockRepository.resultList.value.add(processBlock)
                    BlockRepository.setDefaultValues()
                }

                val outputScript = OutputScript(context)
                when(output){
                    Output.SAVE -> outputScript.file()
                    Output.DEFAULT -> {}
                }

            }
            launch {
                script.await()
                BlockRepository.isScriptRunning.value = false
                cancel()
            }
        }
    }
}