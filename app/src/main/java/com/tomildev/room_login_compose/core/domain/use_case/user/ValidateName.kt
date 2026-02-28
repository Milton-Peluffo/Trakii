package com.tomildev.room_login_compose.core.domain.use_case.user

import com.tomildev.room_login_compose.core.domain.model.user.UserValidationError
import com.tomildev.room_login_compose.core.domain.model.user.UserValidationResult
import javax.inject.Inject

class ValidateName @Inject constructor() {

    val usernameRegex = Regex("^[A-Za-z0-9_]+$")

    fun execute(name: String) : UserValidationResult {
        if (!usernameRegex.matches(name)) {
            UserValidationResult.Error(
                error = UserValidationError.InvalidName
            )
        }

        if (name.length <= 3) {
            UserValidationResult.Error(
                error = UserValidationError.TooShort
            )
        }

        return UserValidationResult.Success
    }
}