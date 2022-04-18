package com.example.movie28022022

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movie28022022.ui.theme.Movie28022022Theme
import com.example.movie28022022.models.Movie
import com.example.movie28022022.models.getMovies
import com.example.movie28022022.navigation.MovieNavigation
import com.example.movie28022022.screens.HomeScreen
import com.example.movie28022022.viewmodel.FavoritesViewModel
import androidx.compose.material.Icon as Icon


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val favoritesViewModel: FavoritesViewModel by viewModels()
//        favoritesViewModel.favoriteMovies
//
//        val favs = favoritesViewModel.getAllMovies()

        setContent {
            Movie28022022Theme {
                // A surface container using the 'background' color from the theme
                Surface (
                    color = MaterialTheme.colors.background
                ) {
                    MovieNavigation()
                }
            }
        }
    }




    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy called")
    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Movie28022022Theme {
        MovieNavigation()
    }
}

