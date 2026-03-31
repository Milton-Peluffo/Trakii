package com.tomildev.room_login_compose.features.auth.data.repository

import com.tomildev.room_login_compose.core.domain.model.error.DataError
import com.tomildev.room_login_compose.core.domain.model.user.User
import com.tomildev.room_login_compose.core.domain.util.Result
import com.tomildev.room_login_compose.features.auth.domain.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.exceptions.UnauthorizedRestException
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

/**
 * Implementation of [AuthRepository] that handles authentication using Supabase Auth.
 *
 * This repository manages user sign up and email OTP verification by interacting
 * with the [SupabaseClient] authentication module. It also maps Supabase-specific
 * exceptions into domain layer [DataError.Network] types to provide consistent
 * and safe error handling across the app.
 */
class AuthRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : AuthRepository {

    override suspend fun signUp(
        user: User,
        password: String
    ): Result<Unit, DataError.Network> {
        return try {
            supabaseClient.auth.signUpWith(Email) {
                email = user.email
                this.password = password
                data = buildJsonObject {
                    put("display_name", user.name)
                }
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(error = mapSupabaseError(e))
        }
    }

    override suspend fun verifyOtp(email: String, otp: String): Result<Unit, DataError.Network> {
        return try {
            supabaseClient.auth.verifyEmailOtp(
                type = OtpType.Email.SIGNUP,
                email = email,
                token = otp
            )
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(error = mapSupabaseError(e))
        }
    }

    private fun mapSupabaseError(e: Exception): DataError.Network {
        return when (e) {
            is AuthRestException -> {
                val message = e.message ?: ""

                when {
                    message.contains("user_already_exists") -> DataError.Network.Conflict
                    else -> DataError.Network.Unknown
                }
            }

            is HttpRequestException -> {
                DataError.Network.Timeout
            }

            is UnauthorizedRestException -> {
                DataError.Network.InvalidOtp
            }

            is java.io.IOException -> {
                DataError.Network.NoInternet
            }

            is RestException -> {
                DataError.Network.ServiceUnavailable
            }

            else -> DataError.Network.Unknown
        }
    }
}