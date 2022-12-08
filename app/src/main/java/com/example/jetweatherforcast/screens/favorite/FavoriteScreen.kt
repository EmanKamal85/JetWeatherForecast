package com.example.jetweatherforcast.screens.favorite

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherforcast.model.Favorite
import com.example.jetweatherforcast.navigation.WeatherScreens
import com.example.jetweatherforcast.widget.WeatherAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(navController: NavController,
                   favoriteViewModel: FavoriteViewModel = hiltViewModel()){
    Scaffold(topBar = {
        WeatherAppBar(navController = navController,
        title = "Favorite Cities",
        icon = Icons.Default.ArrowBack,
        isMainScreen = false){
            navController.popBackStack()
        }
    }) {
        Surface(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
                val list = favoriteViewModel.favList.collectAsState().value
                LazyColumn{
                    Log.d("LazyColumn", "FavoriteScreen: LazyColumn implemented")

                    items(items = list){
                        Log.d("Favorite Item", "FavoriteScreen: ${it.city}")
                        CityRow(it, navController = navController, favoriteViewModel = favoriteViewModel)
                    }

                }

            }

        }
    }
}

@Composable
fun CityRow(favorite : Favorite,
            navController: NavController,
            favoriteViewModel: FavoriteViewModel) {

        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name + "/${favorite.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color(0xffb2dfdb)
        ) {
            
            Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(text = favorite.city, modifier = Modifier.padding(start = 4.dp))
                Surface(modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xffd1e3e1)) {
                    Text(text = favorite.country, modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption)

                }
                
                Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete Icon",
                modifier = Modifier.clickable {
                                              favoriteViewModel.deleteFavorite(favorite)
                },
                tint = Color.Red.copy(0.3f))

            }

        }
}
