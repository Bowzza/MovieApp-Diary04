package com.example.movie28022022.screens

import android.widget.HorizontalScrollView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.movie28022022.R
import com.example.movie28022022.models.Movie
import com.example.movie28022022.models.getMovies
import com.example.movie28022022.viewmodel.FavoritesViewModel


@Composable
fun DetailScreen(navController: NavController = rememberNavController(), movieId: String?, vm: FavoritesViewModel){
    val movie = filterMovie(movieId = movieId)
    MainContent(movie, navController) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CreateCardDetail(movie = movie, vm)

                Spacer(modifier = Modifier.height(8.dp))

                Divider()

                Text(text = "${movie.title}", style = MaterialTheme.typography.h5)

                HorizontalScrollView(movie)
            }
        }

    }
}

@Composable
fun MainContent(movie: Movie, navController: NavController, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = movie.title)
                }
            }
        }) {
        Column(
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CreateCardDetail(
    movie: Movie, vm: FavoritesViewModel = viewModel(), onItemClick: (String) -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = CircleShape,
                elevation = 6.dp
            ) {
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = "seas",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }

            Column {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                if (movie.director != "N/A") {
                    Text(text = "Director: " + movie.director, fontSize = 13.sp)
                } else {
                    Text(text = "No director", fontSize = 13.sp)
                }
                Text(text = "Released: " + movie.year, fontSize = 13.sp)
                AnimatedVisibility(visible = expanded) {
                    Column(Modifier.padding(4.dp)) {
                        Text(text = "Plot: " + movie.plot, fontSize = 13.sp)
                        Divider(color = Color.LightGray, thickness = 0.5.dp)
                        Text(text = "Genre: " + movie.genre, fontSize = 13.sp)
                        Text(text = "Actors: " + movie.actors, fontSize = 13.sp)
                        Text(text = "Rating: " + movie.rating, fontSize = 13.sp)
                    }
                }
                IconButton(modifier = Modifier.padding(horizontal = 0.dp), onClick = {
                    expanded = !expanded
                }) {
                    if (!expanded) {
                        Icon(
                            Icons.Default.KeyboardArrowUp,
                            contentDescription = "More Details"
                        )
                    } else {
                        Icon(
                            Icons.Default.KeyboardArrowDown,
                            contentDescription = "More Details"
                        )
                    }
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                FavoriteIconDetail(vm = vm, movie = movie)
            }

        }
    }
}

@Composable
fun FavoriteIconDetail(vm: FavoritesViewModel, movie: Movie) {
    var inFavorite by rememberSaveable { mutableStateOf(false)}
    if(inFavorite || vm.checkMovies(movie)) {
        IconButton(onClick = {
            vm.removeMovie(movie)
            inFavorite = !inFavorite
        }) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = Color.Cyan
            )
        }
    } else {
        IconButton(onClick = {
            vm.addMovie(movie)
            inFavorite = !inFavorite
        }) {
            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = "No Favorite",
                tint = Color.Cyan
            )
        }
    }

}

@Composable
fun HorizontalScrollView(movie: Movie = getMovies()[0]) {
    LazyRow {
        items(movie.images) { image ->
            Card(modifier = Modifier.padding(12.dp).size(240.dp), elevation = 4.dp) {
                AsyncImage(model = image, contentDescription = "image")
            }
        }
    }
}

fun filterMovie(movieId: String?) : Movie {
    return getMovies().filter { it.id == movieId }[0]
}