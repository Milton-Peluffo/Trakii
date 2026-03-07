package com.tomildev.room_login_compose.core.domain.util

import com.tomildev.room_login_compose.core.domain.model.error.Error

sealed class Result<out D, out E : Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>()
    data class Error<out E : com.tomildev.room_login_compose.core.domain.model.error.Error>(val error: E) :
        Result<Nothing, E>()
}