package com.example.mapapplication.model.account


/**
 * Throws:
 *      Account is not created
 *      Account already created
 *      Empty field: username, email, password
 *      Password is not correct
 *      Auth exception
 */
interface AccountRepository {

    /**
     * Throws:
     *      Account is not created
     *      Password is not correct
     *      Empty field: email, password
     */
    fun signIn(email: String, password: String)

    /**
     * Throws:
     *      Empty field: username, email, password
     *      Account already created
     */
    fun signUp(signUpData: SignUpData)

    fun logout()

    /**
     * Throws:
     *      Auth exception
     */
    fun getAccount(): Account

    fun changeUsername(newUsername: String)

    /**
     *  Throws:
     *      Password is not correct
     */
    fun changePassword(currentPassword: String, newPassword: String)

    fun addListener(listener: AccountListener)

    fun removeListener(listener: AccountListener)

}