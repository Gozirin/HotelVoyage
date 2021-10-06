package com.example.hbapplicationgroupa.network

import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserById
import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserByIdData
import com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel.UsersByPageNumber
import retrofit2.Response
import retrofit2.http.*

/** set up interface for network calls. Annotation @POST makes request to add new data to the API,
 * @GET requests data from API, @PATCH and @PUT updates data in API. @PATCH modifies while @PUT replaces. **/


interface HBAuthenticationModuleApi {

    @POST("add-user")
    suspend fun addUserData(@Body userByIdData: UserByIdData): Response<UserById>


    @GET("login")
    suspend fun getUserLogin(EmailAddress: String, Password: String): Response<UserById>


    @PATCH("update-password")
    suspend fun updateUserPassword(
        oldPassword: String,
        newPassword: String
    ): Response<UserById>


    @POST("forgot-password")
    suspend fun verifyForgotPassword(EmailAddress: String): Response<UserById>


    @PATCH("reset-password")
    suspend fun resetPassword(
        EmailAddress: String,
        Token: String,
        Password: String
    ): Response<UserById>


    @POST("confirm-email")
    suspend fun confirmEmailAddress(
        EmailAddress: String,
        Token: String
    ): Response<UserById>

}