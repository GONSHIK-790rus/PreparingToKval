package com.example.preparingtokval.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.preparingtokval.App
import com.example.preparingtokval.R
import com.example.preparingtokval.data.models.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AccountDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_account_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initLabels()

        findViewById<Button>(R.id.btnLogOut).setOnClickListener {
            logOut()
        }

        findViewById<Button>(R.id.btnSaveChanges).setOnClickListener {
            updateProfileData()
        }

        findViewById<Button>(R.id.btnFlights).setOnClickListener {
            startFlightsActivity()
        }

        findViewById<Button>(R.id.btnFavourites).setOnClickListener {
            startFavouritesActivity()
        }
    }

    private fun initLabels() {
        findViewById<EditText>(R.id.editTxtNickname).setText(App.authorizedUser!!.nickName)
        findViewById<TextView>(R.id.txtViewLoginValue).text = App.authorizedUser!!.login
    }

    private fun logOut() {
        synchronized(this) {
            val intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)
            App.authorizedUser = null
            finish()
        }
    }

    private fun updateProfileData() {
        val nickname = findViewById<EditText>(R.id.editTxtNickname).text.toString()
        if (nickname.isEmpty()) {
            Toast.makeText(App.appContext, "Поле никнейм не может быть пустым", Toast.LENGTH_LONG)
                .show()
            return
        }

        var nickFromDb: String
        runBlocking {
            nickFromDb = App.dataBase.userDao().getNickName(nickname).toString()
        }
        if (nickFromDb.isEmpty()) {
            Toast.makeText(App.appContext, "Этот никнейм уже занят", Toast.LENGTH_LONG).show()
            return
        }

        val updatedUser = User(
            id = App.authorizedUser!!.id,
            login = App.authorizedUser!!.login,
            password = App.authorizedUser!!.password,
            nickName = nickname
        )
        lifecycleScope.launch {
            App.dataBase.userDao().updateUser(updatedUser)
        }
        Toast.makeText(App.appContext, "Никнейм успешно обновлен", Toast.LENGTH_LONG).show()
    }

    private fun startFlightsActivity() {
        val intent = Intent(this, FlightsActivity::class.java)
        startActivity(intent)
    }

    private fun startFavouritesActivity() {
        val intent = Intent(this, FavouritesActivity::class.java)
        startActivity(intent)
    }
}