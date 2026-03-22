package com.tomildev.room_login_compose.features.auth.data.repository

import com.tomildev.room_login_compose.core.domain.model.error.DataError
import com.tomildev.room_login_compose.core.domain.model.user.User
import com.tomildev.room_login_compose.core.domain.util.Result
import com.tomildev.room_login_compose.features.auth.data.remote.dto.ProfileDto
import com.tomildev.room_login_compose.features.auth.data.remote.dto.SignUpRequestDto
import com.tomildev.room_login_compose.features.auth.data.remote.service.AuthService
import com.tomildev.room_login_compose.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Implementation of [AuthRepository] that handles user registration using a remote API.
 *
 * This class coordinates the sign-up flow by first creating the user account
 * and then creating the user's profile with additional data like display name.
 *
 * It also handles errors from the network and maps them into [DataError.Network]
 * so they can be used safely in the domain layer.
 *
 * @property authService Service used to perform authentication requests.
 */
class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun registerUser(
        user: User,
        password: String
    ): Result<User, DataError.Network> {
        return try {
            val authResponse = authService.signUp(
                request = SignUpRequestDto(
                    email = user.email,
                    password = password,
                    data = mapOf("display_name" to user.name)
                )
            )
            authService.createProfile(
                profile = ProfileDto(
                    id = authResponse.user.id,
                    displayName = user.name
                )
            )

            Result.Success(user)
        } catch (e: Exception) {
            val networkError = when (e) {
                is retrofit2.HttpException -> {
                    when (e.code()) {
                        500, 503 -> DataError.Network.ServiceUnavailable
                        412, 422 -> DataError.Network.Conflict
                        408 -> DataError.Network.Timeout
                        else -> DataError.Network.Unknown
                    }
                }

                is java.io.IOException -> DataError.Network.NoInternet
                else -> DataError.Network.Unknown
            }
            Result.Error(networkError)
        }
    }
}