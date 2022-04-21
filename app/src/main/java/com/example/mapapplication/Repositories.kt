package com.example.mapapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.mapapplication.model.SharedPreferencesAccount
import com.example.mapapplication.model.account.AccountRepository
import com.example.mapapplication.model.account.SQLAccountManager
import com.example.mapapplication.model.checkin.CheckInManager
import com.example.mapapplication.model.sqlite.AppSQLiteHelper

object Repositories {

    private lateinit var applicationContext: Context

    private val database: SQLiteDatabase by lazy<SQLiteDatabase> {
        AppSQLiteHelper(applicationContext).writableDatabase
    }

    lateinit var currentAccount:SharedPreferencesAccount

    val accountsRepository: AccountRepository by lazy {
        SQLAccountManager(database, currentAccount)
    }

    val checkInsRepository: CheckInManager = CheckInManager()

    fun initAppContext (context: Context) {
        applicationContext = context
    }

    fun initCurrentAccount () {
        currentAccount = SharedPreferencesAccount(applicationContext)
    }
}