package com.example.movie28022022.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movie28022022.screens.DetailScreen
import com.example.movie28022022.screens.FavouriteScreen
import com.example.movie28022022.screens.HomeScreen
import com.example.movie28022022.viewmodel.FavoritesViewModel

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    val vm: FavoritesViewModel = viewModel()
    
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name){
        composable(MovieScreens.HomeScreen.name) { HomeScreen(navController = navController, vm = vm)}
        composable(MovieScreens.FavouriteScreen.name) { FavouriteScreen(navController = navController, vm = vm) }
        composable(MovieScreens.DetailScreen.name+"/{movie}",
        arguments = listOf(navArgument("movie") {
            type = NavType.StringType
        })) { backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movie"), vm)
        }
    }
}