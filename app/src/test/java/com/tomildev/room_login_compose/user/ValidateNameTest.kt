package com.tomildev.room_login_compose.user

import com.tomildev.room_login_compose.core.domain.model.user.UserValidationError
import com.tomildev.room_login_compose.core.domain.model.user.UserValidationResult
import com.tomildev.room_login_compose.core.domain.use_case.user.ValidateName
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidateNameTest {

    val validateName = ValidateName()

    @Test
    fun `Empty name returns empty field error`(){

        val name = ""
        val result = validateName.execute(name)

        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.EmptyField, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `Name with only spaces returns empty field error`(){

        val name = "   "
        val result = validateName.execute(name)

        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.EmptyField, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `Name with spaces returns invalid name error`(){

        val name = " User "
        val result = validateName.execute(name)

        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.InvalidName, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `Name with special symbols returns invalid name error`(){

        val name = "user-?@"
        val result = validateName.execute(name)

        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.InvalidName, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `Name with fewer than 3 characters returns too short name error`(){

        val name = "Us"
        val result = validateName.execute(name)

        assert(result is UserValidationResult.Error)
        assertEquals(UserValidationError.TooShortName, (result as UserValidationResult.Error).error)
    }

    @Test
    fun `Name with underscores returns success`(){

        val name = "user_"
        val result = validateName.execute(name)

        assert(result is UserValidationResult.Success)
    }

    @Test
    fun `Name with numbers returns success`(){

        val name = "user72"
        val result = validateName.execute(name)

        assert(result is UserValidationResult.Success)
    }

    @Test
    fun `Name with uppercases returns success`(){

        val name = "MyUser"
        val result = validateName.execute(name)

        assert(result is UserValidationResult.Success)
    }

    @Test
    fun `Name with uppercases, numbers and underscore returns success`(){

        val name = "MyUser_72"
        val result = validateName.execute(name)

        assert(result is UserValidationResult.Success)
    }
}