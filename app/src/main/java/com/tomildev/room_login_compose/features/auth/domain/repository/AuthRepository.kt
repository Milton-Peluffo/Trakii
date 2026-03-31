package com.tomildev.room_login_compose.features.auth.domain.repository

import com.tomildev.room_login_compose.core.domain.model.error.DataError
import com.tomildev.room_login_compose.core.domain.model.user.User
import com.tomildev.room_login_compose.core.domain.util.Result

/**
 * Repository interface that defines the contract for user authentication.
 *
 * This repository handles user registration and verification processes, communicating with
 * the data layer to manage user accounts creations.
 */
interface AuthRepository {

    suspend fun signUp(user: User, password: String): Result<Unit, DataError.Network>
    suspend fun verifyOtp(email: String, otp: String): Result<Unit, DataError.Network>
}