package com.example.hbapplicationgroupa.models.generalAdminModule.statisticsModel

import androidx.room.Entity
//table for statistics of customers, hotels and managers
@Entity(tableName = "statistics_table")
data class StatisticsData(
    val id : String,
    val companyName: String,
    val businessEmail: String,
    val businessPhone: String,
    val companyAddress: String,
    val state: String,
    val accountName: String,
    val accountNumber: String
)
