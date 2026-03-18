package com.tomildev.room_login_compose.core.domain.model.error

sealed interface Error
sealed class DataError : Error {
    sealed class Network : DataError() {
        object ServiceUnavailable : Network()
        object Conflict : Network()
        object NoInternet : Network()
        object Timeout : Network()
        object Unknown : Network()
    }
}