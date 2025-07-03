package com.example.mystarwarsapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mystarwarsapp.Screen
import com.example.mystarwarsapp.ui.theme.MyStarWarsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MyStarWarsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(Screen.Home.route) {
                            HomeScreen(onCategorySubmit = { category ->
                                navController.navigate(Screen.Results.createRoute(category))
                            })
                        }
                        composable(
                            route = Screen.Results.route,
                            arguments = listOf(navArgument("category") { type = NavType.StringType})
                        ) { backStackEntry -> // this part is responsible for going to home screen from the category you selected (films,vehicles,etc) 
                            val category = backStackEntry.arguments?.getString("category") ?: ""
                            ResultScreen(category = category, navController = navController)
                        }
                    }
                }
            }
        }
    }
}
