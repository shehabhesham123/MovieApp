package com.example.movieapp.core.navigator

import android.content.Context
import android.content.Intent
import com.example.movieapp.features.movies.app.ui.MainActivity
import com.example.movieapp.features.auth.credentials.Authenticator
import com.example.movieapp.features.login.ui.LoginActivity
import javax.inject.Inject

class Navigator @Inject constructor(private val authenticator: Authenticator) {

    fun showMain(context: Context) {
        if (authenticator.isLogin()) {
            context.startActivity(Intent(context, MainActivity::class.java))
        } else {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}