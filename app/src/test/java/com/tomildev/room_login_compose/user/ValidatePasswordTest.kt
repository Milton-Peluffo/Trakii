package com.tomildev.room_login_compose.user

import com.tomildev.room_login_compose.core.domain.model.user.UserValidationError
import com.tomildev.room_login_compose.core.domain.model.user.UserValidationResult
import com.tomildev.room_login_compose.core.domain.use_case.user.ValidatePassword
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidatePasswordTest {

    val validatePassword = ValidatePassword()

    @Test
    fun `Empty password returns empty field error`(){
        val password = ""
        val result = validatePassword.execute(password)
        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.EmptyField, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `Password with only spaces returns empty field error`(){
        val password = "   "
        val result = validatePassword.execute(password)
        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.EmptyField, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `Password with only numbers returns invalid password error`(){
        val password = "12345678"
        val result = validatePassword.execute(password)
        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.InvalidPassword, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `Password with less than 8 characters returns password too short error`(){
        val password = "@1234"
        val result = validatePassword.execute(password)
        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.TooShortPassword, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `Password with special character, letters and numbers returns success`(){
        val password = "@Pass234"
        val result = validatePassword.execute(password)
        assert(result is UserValidationResult.Success)
    }
}