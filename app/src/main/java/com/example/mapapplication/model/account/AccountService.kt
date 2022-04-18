package com.example.mapapplication.model.account

import com.example.mapapplication.model.*

typealias AccountListener = (accounts: MutableList<Account>) -> Unit

class AccountService : AccountInterface {

    private var currentAccount: Account? = null
    private val accounts = mutableListOf<Account>()
    private val listeners = mutableSetOf<AccountListener>()

    override suspend fun isSignedIn(): Boolean = currentAccount != null

    override suspend fun signIn(email: String, password:String) {
        if (email.isBlank()) throw EmptyFieldException(Field.Email)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)
        val account = getAccountByEmail(email) ?: throw AccountIsNotCreatedException()
        if (account.password != password) throw PasswordIsNotCorrect()
    }

    override suspend fun signUp(data: SignUpData) {
        data.validate()

        if(getAccountByEmail(data.email) != null) throw AccountAlreadyCreatedException()
        accounts.add(Account(data.username, data.firstName, data.secondName, data.email, data.password))
        notifyChanges()
        currentAccount = accounts.last()
        notifyChanges()
    }

    override fun logout() {
        currentAccount = null
    }

    override fun getAccount() = currentAccount

    private fun getAccountByEmail(email: String) = accounts.firstOrNull{ it.email == email }

    fun addListener(listener: AccountListener){
        listeners.add(listener)
    }

    fun removeListener(listener: AccountListener){
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(accounts) }
    }

}