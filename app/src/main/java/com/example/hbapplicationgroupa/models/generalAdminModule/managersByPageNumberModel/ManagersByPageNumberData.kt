package com.example.hbapplicationgroupa.models.generalAdminModule.managersByPageNumberModel

import androidx.room.Entity
//table for managers statistics using pagination
@Entity(tableName = "managers_statistics_by_pageNumber_table")
data class ManagersByPageNumberData(
    val id : String,
    val companyName: String,
    val businessEmail: String,
    val businessPhone: String,
    val companyAddress: String,
    val state: String,
    val accountName: String,
    val accountNumber: String
)
