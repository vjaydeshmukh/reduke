package org.wit.reduke.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*
import org.wit.post.R
import org.wit.reduke.main.MainApp
import org.wit.reduke.models.UserModel

class RedukeLoginActivity : AppCompatActivity(), AnkoLogger {
    lateinit var app: MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        app = application as MainApp
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val redukePref = RedukeSharedPreferences(this)
        loginButton.setOnClickListener {
            var users = app.users.findAll()
            var foundUser: UserModel? = users.find { p -> p.email == enteredEmail.text.toString() }
            if (foundUser != null) {
                if (enteredEmail.text.toString() == foundUser.email && enteredPassword.text.toString() == foundUser.password) {
                    redukePref.setCurrentUserName(foundUser.name)
                    redukePref.setCurrentUserEmail(foundUser.email)
                    redukePref.setCurrentUserPassword(foundUser.password)
                } else {
                    toast(R.string.toast_InvalidCreds)
                }
            } else {
                error { "foundUser is null" }
            }
        }
        navToRegisterButton.setOnClickListener {
            startActivityForResult<RedukeRegisterActivity>(0)
        }
    }
}