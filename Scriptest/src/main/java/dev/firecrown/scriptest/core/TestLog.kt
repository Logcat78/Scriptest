package dev.firecrown.scriptest.core

import android.util.Log
import dev.firecrown.scriptest.data.entities.LogEntity
import dev.firecrown.scriptest.data.repositories.BlockRepository
import java.text.SimpleDateFormat
import java.util.Date

class TestLog {
    companion object{

        internal val logArray: ArrayList<LogEntity> = arrayListOf()
        private val scriptName = BlockRepository.scriptName

        private fun getTimeStamp(): String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())

        private fun log(script: String, text: String, type: String){
            Log.d("ScriptTest", scriptName.value)
            if(script == scriptName.value) {
                Log.d("ScriptTest", "Add log")
                logArray.add(
                    LogEntity(
                        text = text,
                        type = type,
                        timestamp = getTimeStamp()
                    )
                )
            }
        }

        fun error(script: String, text: String) = log(script, text, "error")
        fun info(script: String, text: String) = log(script, text, "info")
        fun debug(script: String, text: String) = log(script, text, "debug")
    }
}