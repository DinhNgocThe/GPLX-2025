package com.utc.driverxy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ScanTrafficSignsResult(
    val signCode: String,
    val signName: String,
    val signGroup: String,
    val meaning: String,
    val applicableCases: String,
    val notes: String,
    val hasTrafficSigns: Boolean
)