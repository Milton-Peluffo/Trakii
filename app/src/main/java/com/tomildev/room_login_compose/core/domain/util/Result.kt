package com.tomildev.room_login_compose.core.domain.util

import com.tomildev.room_login_compose.core.domain.model.error.DataError

sealed class Result<out D> {
    data class Success<out D>(val data: D): Result<D>()
    data class Error(val error: DataError): Result<Nothing>()
}