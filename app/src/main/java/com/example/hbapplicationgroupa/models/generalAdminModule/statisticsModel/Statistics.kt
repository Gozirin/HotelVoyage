package com.example.hbapplicationgroupa.models.generalAdminModule.statisticsModel

data class Statistics (
    val statusCode: String,
    val success: Boolean,
    val data:StatisticsData,
    val Message: String,
    val error: String?
)