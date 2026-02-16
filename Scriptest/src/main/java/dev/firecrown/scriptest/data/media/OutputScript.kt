package dev.firecrown.scriptest.data.media

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import dev.firecrown.scriptest.core.TestLog
import dev.firecrown.scriptest.data.json.ScriptHelper
import dev.firecrown.scriptest.data.repositories.BlockRepository.resultList
import dev.firecrown.scriptest.data.repositories.BlockRepository.scriptName
import java.io.File

internal class OutputScript(val context: Context) {

    fun file() {
        val resultJson = ScriptHelper().createResult(
            name = scriptName.value,
            resultList = resultList.value,
            logsList = TestLog.logArray
        )
        val file = File(
            context.getExternalFilesDir("scripts"),
            "scriptest-${scriptName.value}.json"
        )
        file.writeText(resultJson)
    }
}