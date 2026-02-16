package dev.firecrown.scriptest.data.entities

import androidx.compose.runtime.snapshots.Snapshot

internal data class BlockEntity(
    val title: String,
    val text: String,
    val dialogOverlayPosition: String,
    val textField: String?,
    val pointer: List<Float>?,
    val options: List<String>?,
    val snapshot: Boolean,
)