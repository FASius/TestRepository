package com.example.mapapplication.model.account

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf
import com.example.mapapplication.model.*
import com.example.mapapplication.model.sqlite.SQLiteContract.AccountsTable

typealias AccountListener = (account: Account?) -> Unit

class SQLAccountManager(
    private val db: SQLiteDatabase,
    private val accountSettings: SharedPreferencesAccount
) : AccountRepository {

    private var currentAccount: Account? = null
    private val listeners = mutableSetOf<AccountListener>()

    override fun signIn(email: String, password:String) {
        if (email.isBlank()) throw EmptyFieldException(Field.Email)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)
        val id = getIdByEmailAndPassword(email, password)
        currentAccount = getAccountById(id)
        notifyChanges()
        accountSettings.setCurrentAccountId(id)
    }

    override fun signUp(signUpData: SignUpData) {
        signUpData.validate()
        try {
            db.insertOrThrow(
                AccountsTable.TABLE_NAME,
                null,
                contentValuesOf(
                    AccountsTable.COLUMN_EMAIL to signUpData.email,
                    AccountsTable.COLUMN_PASSWORD to signUpData.password,
                    AccountsTable.COLUMN_USERNAME to signUpData.username
                )
            )
        }
        catch (e: SQLiteConstraintException){
            throw AccountAlreadyCreatedException().initCause(e)
        }
        val id = getIdByEmailAndPassword(signUpData.email, signUpData.password)
        currentAccount = getAccountById(id)
        notifyChanges()
        accountSettings.setCurrentAccountId(id)
    }

    override fun logout() {
        currentAccount = null
        accountSettings.setCurrentAccountId(Account.NO_ACCOUNT)
        notifyChanges()
    }

    override fun getAccount() = currentAccount ?: throw AuthException()

    override fun changeUsername(newUsername: String) {
        val id = currentAccount?.id ?: return
        currentAccount?.username = newUsername
        db.update(
            AccountsTable.TABLE_NAME,
            contentValuesOf(AccountsTable.COLUMN_USERNAME to newUsername),
            "${AccountsTable.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
        notifyChanges()
    }

    override fun changePassword(currentPassword: String, newPassword: String) {
        val id = currentAccount?.id ?: return
        db.query(
            AccountsTable.TABLE_NAME,
            arrayOf(AccountsTable.COLUMN_PASSWORD),
            "${AccountsTable.COLUMN_ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        ).use {
            it.moveToFirst()
            val passwordFromDB = it.getString(it.getColumnIndexOrThrow(AccountsTable.COLUMN_PASSWORD))
            if (currentPassword != passwordFromDB) throw  PasswordIsNotCorrect()
        }
        db.update(
            AccountsTable.TABLE_NAME,
            contentValuesOf(AccountsTable.COLUMN_PASSWORD to newPassword),
            "${AccountsTable.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
        notifyChanges()
    }


    private fun getAccountById(id: Long): Account?{
        val cursor = db.query(
            AccountsTable.TABLE_NAME,
            arrayOf(AccountsTable.COLUMN_USERNAME, AccountsTable.COLUMN_PASSWORD, AccountsTable.COLUMN_EMAIL ),
            "${AccountsTable.COLUMN_ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        return cursor.use {
            if (cursor.count == 0) return@use null
            cursor.moveToFirst()
            Account(
                id = id,
                username = cursor.getString(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_USERNAME)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_PASSWORD)),
            )
        }
    }

    private fun getIdByEmailAndPassword(email: String, password: String): Long{
        val cursor = db.query(
            AccountsTable.TABLE_NAME,
            arrayOf(AccountsTable.COLUMN_ID, AccountsTable.COLUMN_PASSWORD ),
            "${AccountsTable.COLUMN_EMAIL} = ?",
            arrayOf(email),
            null, null, null
        )
        return cursor.use {
            if (cursor.count == 0) throw AccountIsNotCreatedException()
            cursor.moveToFirst()
            val passwordFromDB = cursor.getString(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_PASSWORD))
            if (password != passwordFromDB) throw  PasswordIsNotCorrect()
            cursor.getLong(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_ID))
        }
    }

    override fun addListener(listener: AccountListener){
        listeners.add(listener)
    }

    override fun removeListener(listener: AccountListener){
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(currentAccount) }
    }

}