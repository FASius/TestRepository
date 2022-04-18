package com.example.mapapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapapplication.model.account.Account

class SignInViewModel() : ViewModel() {

    val accounts = MutableLiveData<List<Account>>()

}