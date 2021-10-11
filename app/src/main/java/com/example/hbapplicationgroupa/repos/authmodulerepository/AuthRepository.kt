package com.example.hbapplicationgroupa.repos.authmodulerepository

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
import com.example.hbapplicationgroupa.networksss.AuthModuleApiInterface
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authModuleApiInterface: AuthModuleApiInterface): AuthRepositoryInterface {
    override suspend fun addUser(addUserModel: AddUserModel): Response<AddUserResponseModel> {
        return authModuleApiInterface.addUser(addUserModel)
    }

    override suspend fun loginUser(loginUserModel: LoginUserModel): Response<LoginUserResponseModel> {
        return authModuleApiInterface.loginUser(loginUserModel)
    }

    override suspend fun updatePassword(updatePasswordModel: UpdatePasswordModel): Response<UpdatePasswordResponseModel> {
        return authModuleApiInterface.updatePassword(updatePasswordModel)
    }

    override suspend fun forgotPassword(EmailAddress: String): Response<ForgotPasswordResponseModel> {
        return authModuleApiInterface.forgotPassword(EmailAddress)
    }

    override suspend fun resetPassword(resetPasswordModel: ResetPasswordModel): Response<ResetPasswordResponseModel> {
        return authModuleApiInterface.resetPassword(resetPasswordModel)
    }

    override suspend fun confirmEmail(confirmEmailModel: ConfirmEmailModel): Response<ConfirmEmailResponseModel> {
        return authModuleApiInterface.confirmEmail(confirmEmailModel)
    }
}