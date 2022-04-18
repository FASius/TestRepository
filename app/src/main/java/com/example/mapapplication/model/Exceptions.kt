package com.example.mapapplication.model

abstract class AppException() : RuntimeException()

class EmptyFieldException(val field : Field) : AppException()

class AuthException() : AppException()

class PasswordMismatchException() : AppException()

class AccountAlreadyCreatedException() : AppException()

class AccountIsNotCreatedException() : AppException()

class PasswordIsNotCorrect() : AppException()