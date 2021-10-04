package com.example.hbapplicationgroupa.models.generalAdminModule.managerStatisticsModel

import androidx.room.Entity

@Entity(tableName = "manager_statistics_table")
data class ManagerStatisticsData(
    val id : String,
    val companyName: String,
    val businessEmail: String,
    val businessPhone: String,
    val companyAddress: String,
    val state: String,
    val accountName: String,
    val accountNumber: String
)
