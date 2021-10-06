package com.example.hbapplicationgroupa.network.hotelbookingapi

import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserById
import com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel.UsersByPageNumber
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

/** set up interface for network calls. Annotation @POST makes request to add new data to the API,
 * @GET requests data from API, @PATCH and @PUT updates data in API. @PATCH modifies while @PUT replaces. **/

interface HBAdminModuleApi {

    @GET("Users/get-users/")
    suspend fun getUsersByPage(
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int
    ): Response<List<UsersByPageNumber>>


    @PUT("Users/update-user/{userId}")
    suspend fun updateUserById(@Path("userId") userId: String): Response<UserById>


    @GET("Users/get-user/{userId}")
    suspend fun getUserById(@Query("userId") Id: String): Response<UserById>


    @Multipart
    @PATCH("Users/update-user/{userId}/user-photo")
    suspend fun updateUserPhotoByUserId(
        @Part("userId") userId: String,
        @Part image: MultipartBody.Part
    ): Response<UserById>
}