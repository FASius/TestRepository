package com.example.mapapplication

import android.app.Application
import com.example.mapapplication.model.account.AccountService

class App : Application() {

    val accountService = AccountService()
}