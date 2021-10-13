package com.example.hbapplicationgroupa.repository.authmodulerepository

import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserModel
import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserResponseModel
import com.example.hbapplicationgroupa.model.authmodule.confirmemail.ConfirmEmailModel
import com.example.hbapplicationgroupa.model.authmodule.confirmemail.ConfirmEmailResponseModel
import com.example.hbapplicationgroupa.model.authmodule.forgotpassword.ForgotPasswordResponseModel
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserModel
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponseModel
import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordModel
import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordResponseModel
import com.example.hbapplicationgroupa.model.authmodule.updatepassword.UpdatePasswordModel
import com.example.hbapplicationgroupa.model.authmodule.updatepassword.UpdatePasswordResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRepositoryInterface {
    suspend fun addUser(addUserModel: AddUserModel): Response<AddUserResponseModel>
    suspend fun loginUser(loginUserModel: LoginUserModel): Response<LoginUserResponseModel>
    suspend fun updatePassword(updatePasswordModel: UpdatePasswordModel): Response<UpdatePasswordResponseModel>
    suspend fun forgotPassword(EmailAddress: String): Response<ForgotPasswordResponseModel>
    suspend fun resetPassword(resetPasswordModel: ResetPasswordModel): Response<ResetPasswordResponseModel>
    suspend fun confirmEmail(confirmEmailModel: ConfirmEmailModel): Response<ConfirmEmailResponseModel>
    suspend fun saveAuthToken(token:String)
    suspend fun getAuthToken() : Flow<String?>
    suspend fun saveAuthId(id: String)
    suspend fun getAuthId() : Flow<String?>
}