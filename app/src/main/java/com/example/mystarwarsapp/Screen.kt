package com.example.mystarwarsapp

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Results : Screen("results/{category}") {
        fun createRoute(category: String) = "results/$category"
    }
}