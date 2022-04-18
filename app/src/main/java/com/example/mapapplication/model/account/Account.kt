package com.example.mapapplication.model.account

import com.example.mapapplication.model.EmptyFieldException
import com.example.mapapplication.model.Field

data class Account(
    val username:String,
    val firstName:String,
    val secondName:String,
    val email:String,
    val password:String
) {
    fun validate(){
        when{
            username.isBlank() -> throw EmptyFieldException(Field.Username)
            firstName.isBlank() -> throw EmptyFieldException(Field.FirstName)
            secondName.isBlank() -> throw EmptyFieldException(Field.SecondName)
            email.isBlank() -> throw EmptyFieldException(Field.Email)
            password.isBlank() || password.length < 8 -> throw EmptyFieldException(Field.Password)
        }
    }
}

class SignUpData(
    val username:String,
    val firstName:String,
    val secondName:String,
    val email:String,
    val password:String
){
    fun validate() {
        when {
            username.isBlank() -> throw EmptyFieldException(Field.Username)
            firstName.isBlank() -> throw EmptyFieldException(Field.FirstName)
            secondName.isBlank() -> throw EmptyFieldException(Field.SecondName)
            email.isBlank() -> throw EmptyFieldException(Field.Email)
            password.isBlank() || password.length < 8 -> throw EmptyFieldException(Field.Password)
        }
    }
}