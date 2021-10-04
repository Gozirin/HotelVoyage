package com.example.hbapplicationgroupa.models.generalAdminModule.managersByPageNumberModel

import com.example.hbapplicationgroupa.models.generalAdminModule.statisticsModel.StatisticsData

data class ManagersByPageNumber(
    val statusCode: String,
    val success: Boolean,
    val data:ArrayList<ManagersByPageNumberData>,
    val Message: String,
    val error: String?
)
