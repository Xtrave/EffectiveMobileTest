package com.example.effectivemobiletest.navigation

import androidx.navigation.NavController
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.authorization.navigation.LoginNavigation

class AppNavigation: LoginNavigation {
    override fun toMain(navController: NavController) {
        navController.navigate(R.id.action_login_to_mainFlow)
    }
}