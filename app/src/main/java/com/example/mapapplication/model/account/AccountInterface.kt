package com.example.mapapplication.model.account

interface AccountInterface {

    suspend fun isSignedIn() : Boolean

    suspend fun signIn(email: String, password: String)

    suspend fun signUp(signUpData: SignUpData)

    fun logout()

    fun getAccount(): Account?

}