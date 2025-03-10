package com.example.preparingtokval

import android.app.Application
import android.content.Context
import com.example.preparingtokval.data.db.DataBase
import com.example.preparingtokval.data.models.User

class App : Application() {
    companion object {
        lateinit var dataBase: DataBase
            private set
        var authorizedUser: User? = null
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        dataBase = DataBase.getDataBase(applicationContext)
        appContext = applicationContext
    }
}
