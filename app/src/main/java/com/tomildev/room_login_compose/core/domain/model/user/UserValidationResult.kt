package com.tomildev.room_login_compose.core.domain.model.user

sealed class UserValidationResult {
    object Success : UserValidationResult()
    data class Error(val error: UserValidationError) : UserValidationResult()
}

sealed class UserValidationError {
    object EmptyField : UserValidationError()
    object TooShort : UserValidationError()
    object InvalidName : UserValidationError()
    object InvalidEmail : UserValidationError()
    object InvalidPassword : UserValidationError()
}