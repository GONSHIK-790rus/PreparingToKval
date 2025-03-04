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
import com.example.preparingtokval.App
import com.example.preparingtokval.R
import kotlinx.coroutines.runBlocking

class AuthorizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_authorization)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<TextView>(R.id.txtViewCreateAccount).setOnClickListener {
            startRegistrationActivity()
        }

        findViewById<Button>(R.id.btnEnter).setOnClickListener {
            enter()
        }
    }

    private fun startRegistrationActivity() {
        val intent: Intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }

    private fun enter() {
        val login = findViewById<EditText>(R.id.editTxtLogin).text.toString()
        val password = findViewById<EditText>(R.id.editTxtPassword).text.toString()

        if (login.isEmpty() || password.isEmpty()) {
            Toast.makeText(App.appContext, "Введите данные", Toast.LENGTH_LONG).show()
            return
        }

        runBlocking {
            App.authorizedUser = App.dataBase.userDao().getUser(login, password)
        }
        if (App.authorizedUser == null) {
            Toast.makeText(App.appContext, "Проверьте правильность заполнения полей", Toast.LENGTH_LONG).show()
            return
        }

        val intent = Intent(this, AccountDataActivity::class.java)
        startActivity(intent)
        finish()
    }
}
