package com.tomildev.room_login_compose.features.auth.domain.repository

import com.tomildev.room_login_compose.core.domain.model.error.DataError
import com.tomildev.room_login_compose.core.domain.model.user.User
import com.tomildev.room_login_compose.core.domain.util.Result

interface AuthRepository {

    suspend fun registerUser(user: User, password: String): Result<User, DataError.Network>
}