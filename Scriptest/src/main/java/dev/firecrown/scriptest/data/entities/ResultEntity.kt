package dev.firecrown.scriptest.data.entities

import android.os.Parcelable
import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.parcelize.Parcelize

internal data class ResultEntity(
    val textField: String?,
    val snapshot: String?,
    val option: String?,
    val exceptionMessage: String?,
    val timestamp: String
)
