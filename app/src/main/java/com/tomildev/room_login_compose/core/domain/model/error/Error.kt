package com.tomildev.room_login_compose.core.domain.model.error

sealed interface Error {
    sealed class DataError : Error {
        sealed class NetWork : DataError() {
            object ServiceUnavailable : NetWork()
            object Conflict : NetWork()
            object NoInternet : NetWork()
            object UnkNown : NetWork()
        }
    }
}