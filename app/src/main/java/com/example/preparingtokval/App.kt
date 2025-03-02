package com.example.preparingtokval

import android.app.Application
import com.example.preparingtokval.data.db.DataBase

class App : Application() {
    companion object {
        lateinit var dataBase: DataBase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        dataBase = DataBase.getDataBase(applicationContext)
    }
}
