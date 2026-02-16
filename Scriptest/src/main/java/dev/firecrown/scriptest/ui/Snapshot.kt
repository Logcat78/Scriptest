package dev.firecrown.scriptest.ui

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.drawToBitmap
import java.io.ByteArrayOutputStream
import java.util.Base64


@Composable
internal fun TakeSnapshot(snapshot: (String) -> Unit){
    val composeView = LocalView.current

    val bitmap = composeView
        .drawToBitmap()

    val stream = ByteArrayOutputStream()

    bitmap.compress(
        Bitmap.CompressFormat.PNG,
        100,
        stream
    )

    val screenshotBase64 = Base64
        .getEncoder()
        .encodeToString(stream.toByteArray())

    snapshot(screenshotBase64)
}