package com.example.do_an.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.do_an.ui.screen.LoginScreen
import com.example.do_an.ui.screen.ProductListScreen
import com.example.do_an.ui.screen.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {

        // Màn hình đăng nhập
        composable("login") {
            LoginScreen(
                onLoginClick = {
                    navController.navigate("productList") {
                        popUpTo("login") { inclusive = true } // Xoá login khỏi backstack
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        // Màn hình đăng ký
        composable("register") {
            RegisterScreen(
                onRegisterClick = {
                    navController.popBackStack() // Quay lại màn hình login sau khi đăng ký
                }
            )
        }

        // Màn hình danh sách sản phẩm
        composable("productList") {
            ProductListScreen(
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo("productList") { inclusive = true } // Quay lại login và xoá productList khỏi backstack
                    }
                }
            )
        }
    }
}
