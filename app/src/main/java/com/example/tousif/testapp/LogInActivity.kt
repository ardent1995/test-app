package com.example.tousif.testapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.widget.Button
import android.widget.Toast
import com.example.tousif.testapp.util.PreferenceManager

class LogInActivity : AppCompatActivity() {

    private val userNameTextInputEditText by lazy { findViewById<TextInputEditText>(R.id.tiet_username) }
    private val passwordTextInputEditText by lazy { findViewById<TextInputEditText>(R.id.tiet_password) }
    private val logInButton by lazy { findViewById<Button>(R.id.btn_log_in) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        logInButton.setOnClickListener {
            if(userNameTextInputEditText.text.toString() == "admin" && passwordTextInputEditText.text.toString() == "123456"){
                PreferenceManager.writetoSharedPreference(this@LogInActivity,true)
                startActivity(Intent(this@LogInActivity,StudentListActivity::class.java))
                finish()
            }else{
                Toast.makeText(this@LogInActivity,"Try again.",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        if(PreferenceManager.readFromSharedPreference(this)){
            startActivity(Intent(this,StudentListActivity::class.java))
            finish()
        }
        super.onStart()
    }
}
