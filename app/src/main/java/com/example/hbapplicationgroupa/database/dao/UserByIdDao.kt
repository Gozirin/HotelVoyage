package com.example.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseItem
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel

@Dao
interface UserByIdDao {
    @Query("SELECT * FROM getUserById")
    fun getUserById(): LiveData<List<GetUserByIdResponseItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewUserDetails(updatedUserDetail: UpdateUserByIdModel)

    @Delete
    fun removeUserDetails()
}