package com.example.mapapplication.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapapplication.model.AccountIsNotCreatedException
import com.example.mapapplication.model.EmptyFieldException
import com.example.mapapplication.model.Field
import com.example.mapapplication.model.PasswordIsNotCorrect
import com.example.mapapplication.model.account.AccountRepository

class SignInViewModel(
    private val accountRepository: AccountRepository,
): ViewModel() {

    private val _state = MutableLiveData(State())
    val state : LiveData<State> = _state

    fun signIn(email:String, password:String) {
        try {
            accountRepository.signIn(email, password)
            navigateToTabs()
        } catch (e: PasswordIsNotCorrect){
            showPasswordError()
        } catch (e: EmptyFieldException){
            showEmptyFieldError(e)
        } catch (e: AccountIsNotCreatedException) {
            showAuthError()
        }
    }

    private fun navigateToTabs(){
        _state.value = State(successAuth = true)
    }

    private fun showAuthError(){
        _state.value = State(authError = true)
    }

    private fun showPasswordError(){
        _state.value = State(passwordMismatch = true)
    }

    private fun showEmptyFieldError(e: EmptyFieldException){
        _state.value = State(
            emailError = e.field == Field.Email,
            passwordError = e.field == Field.Password
        )
    }

    data class State(
        val successAuth: Boolean = false,
        val authError: Boolean = false,
        val emailError: Boolean = false,
        val passwordError: Boolean = false,
        val passwordMismatch: Boolean = false
    )
}