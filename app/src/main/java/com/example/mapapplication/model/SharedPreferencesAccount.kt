package com.example.mapapplication.model

import android.content.Context
import com.example.mapapplication.model.account.Account

class SharedPreferencesAccount(
    appContext: Context
) {

    private val sharedPreferences = appContext
        .getSharedPreferences(ACCOUNT, Context.MODE_PRIVATE)


    fun setCurrentAccountId(id: Long){
        sharedPreferences.edit().putLong(CURRENT_ACCOUNT_ID, id).apply()
    }

    fun getCurrentAccountId(): Long = sharedPreferences.getLong(CURRENT_ACCOUNT_ID, Account.NO_ACCOUNT)

    companion object{
        private const val ACCOUNT = "account"
        private const val CURRENT_ACCOUNT_ID = "CURRENT_ACCOUNT_ID"
    }
}