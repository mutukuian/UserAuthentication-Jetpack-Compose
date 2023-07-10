package com.example.recruittutorial.routers

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recruittutorial.authscreens.LoginScreen
import com.example.recruittutorial.authscreens.RegisterScreen

@Composable
fun SetupNavGraph(
    navController:NavHostController
){

    NavHost(
        navController = navController,
        startDestination = "register_screen"
        ){
        composable(
            Screen.RegisterScreen.route
        ){
            RegisterScreen(
                navController = navController
            )
        }
        composable(
           Screen.LoginScreen.route
        ){
            LoginScreen(
                navController = navController
            )
        }
    }
}