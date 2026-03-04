package com.tomildev.room_login_compose.user

import com.tomildev.room_login_compose.core.domain.model.user.UserValidationError
import com.tomildev.room_login_compose.core.domain.model.user.UserValidationResult
import com.tomildev.room_login_compose.core.domain.use_case.user.ValidateConfirmPassword
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ValidateConfirmPasswordTest {

    val validateConfirmPassword = ValidateConfirmPassword()

    @Test
    fun `Empty confirm password returns empty field error`() {
        val password = ""
        val confirmPassword = ""
        val result = validateConfirmPassword.execute(password, confirmPassword)
        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.EmptyField, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `Confirm password with only spaces returns empty field error`() {
        val password = ""
        val confirmPassword = "    "
        val result = validateConfirmPassword.execute(password, confirmPassword)
        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.EmptyField, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `When confirm password do not match returns PasswordDoNotMatch error`() {
        val password = "@1234"
        val confirmPassword = "@A12373"
        val result = validateConfirmPassword.execute(password, confirmPassword)
        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.PasswordDoNotMatch, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `When confirm password matches the password, returns success`() {
        val password = "@54321"
        val confirmPassword = "@54321"
        val result = validateConfirmPassword.execute(password, confirmPassword)
        assert(result is UserValidationResult.Success)
    }
}