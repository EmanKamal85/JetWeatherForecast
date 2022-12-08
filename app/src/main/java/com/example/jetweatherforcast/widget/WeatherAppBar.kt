package com.example.jetweatherforcast.widget

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherforcast.model.Favorite
import com.example.jetweatherforcast.navigation.WeatherScreens
import com.example.jetweatherforcast.screens.favorite.FavoriteViewModel

//@Preview
@Composable
fun WeatherAppBar(
    title : String = "Title",
    icon : ImageVector? = null,
    isMainScreen : Boolean = true,
    elevation : Dp = 0.dp,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClicked : () -> Unit = {},
    onButtonClicked : () -> Unit = {}
    ){
    val showDialog = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val showIt = remember {
        mutableStateOf(false)
    }

    if (showDialog.value){
        ShowSettingDropDownMenu(showDialog = showDialog,
            navController = navController)
    }
    TopAppBar(title = { Text(text = title)},
    navigationIcon = {
            if (icon != null){
                Icon(imageVector = icon, contentDescription = "Navigation Icon",
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier.clickable {
                    onButtonClicked.invoke()
                })
            }

        if (isMainScreen){
            val isAlreadyFavList = favoriteViewModel.favList.collectAsState().value
                .filter { item ->
                    (item.city == title.split(",")[0])
                }
            if (isAlreadyFavList.isNullOrEmpty()){

                Icon(imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    modifier = Modifier
                        .scale(0.9f)
                        .clickable {
                            val dataList = title.split(",")
                            favoriteViewModel.insertFavorite(
                                Favorite(
                                    city = dataList[0],
                                    country = dataList[1]
                                )
                            ).run { showIt.value = true }
                        },
                    tint = Color.Red.copy(alpha = 0.6f))
            }else{
                showIt.value = false
                Icon(imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite Icon",
                    modifier = Modifier
                        .scale(0.9f),
                    tint = Color.Red.copy(alpha = 0.6f))
                //Box{}
            }
            ShowToast(context, showIt)
        }
    }, elevation = elevation,
        actions = {
            if (isMainScreen){
                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon" )
                }
                IconButton(onClick = {showDialog.value = true
                    Log.d("Options Button", "WeatherAppBar: Options clicked ${showDialog.value}")}) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More Options")
                }
            }else Box{}

        },
    backgroundColor = Color.Transparent)


//        Text(text = title,
//        color = MaterialTheme.colors.secondary,
//        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)




}

@Composable
fun ShowToast(context: Context, showIt: MutableState<Boolean>) {
    if (showIt.value){
        Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show()
    }

}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {

    var expanded by remember {
        mutableStateOf(true)
    }

    val items = mutableListOf("Favorites", "About", "Setting")

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 45.dp, right = 20.dp)) {
        
        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false},
        modifier = Modifier
            .width(140.dp)
            .background(Color.White)) {
            items.forEachIndexed{index, text ->
                DropdownMenuItem(onClick = {expanded = false
                                    showDialog.value = false}) {
                Icon(imageVector = when(text){
                                "Favorites" -> Icons.Default.FavoriteBorder
                                "About" -> Icons.Default.Info
                                else -> Icons.Default.Settings
                                             } , contentDescription = null,
                    tint = Color.LightGray)
                Text(text = text,
                modifier = Modifier.clickable { navController.navigate(
                        when(text) {
                            "Favorites" -> WeatherScreens.FavoriteScreen.name
                            "About" -> WeatherScreens.AboutScreen.name
                            else -> WeatherScreens.SettingsScreen.name
                        } ) },
                fontWeight = FontWeight.W300
                )
                }
            }
        }

    }

}

