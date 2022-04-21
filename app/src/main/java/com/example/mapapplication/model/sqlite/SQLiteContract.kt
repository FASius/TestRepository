package com.example.mapapplication.model.sqlite

object SQLiteContract {

    object AccountsTable{
        const val TABLE_NAME = "accounts"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
    }

    object CheckInTable{
        const val TABLE_NAME = "check_ins"
        const val COLUMN_ID = "id"
        const val COLUMN_CREATED_AT = "created_at"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_LOCATION = "location"
        const val COLUMN_LATITUDE = "latitude"
        const val COLUMN_LONGITUDE = "longitude"
    }

    object AccountsCheckIns{
        const val TABLE_NAME = "accounts_check_ins"
        const val COLUMN_ACCOUNT_ID = "account_id"
        const val COLUMN_CHECK_IN_ID = "check_in_id"
    }

}