package com.example.jetweatherforcast.screens.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherforcast.model.Unit
import com.example.jetweatherforcast.widget.WeatherAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController,
                   settingsViewModel: SettingsViewModel = hiltViewModel()){
    Scaffold(topBar = { WeatherAppBar(navController = navController,
                                      title = "Settings",
                                      icon = Icons.Default.ArrowBack,
                                      isMainScreen = false
        ){
        navController.popBackStack()
    }}) {

        
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Center, 
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Change Units of measurement",
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                val unitToggleState = remember {
                    mutableStateOf(false)
                }

                val measurementList = listOf("Imperial (F)", "Metric (C)")


                val choicesFromdb = settingsViewModel.unitList.collectAsState().value

                val defaultChoice = if(choicesFromdb.isNullOrEmpty()) measurementList[0]
                else choicesFromdb[0].unit

                val choiceState = remember {
                    mutableStateOf(defaultChoice)
                }

                IconToggleButton(checked = !unitToggleState.value,
                    onCheckedChange ={
                        unitToggleState.value = !it
                        //unitToggleState.value = it
                        if (unitToggleState.value){
                            choiceState.value = measurementList[0]
                        }else{
                            choiceState.value = measurementList[1]
                        }

                    },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(5.dp)
                    .clip(shape = RectangleShape)
                    .background(
                        Color.Magenta.copy(alpha = 0.4f)
                    )) {
                    Text(text = if (unitToggleState.value) "Fahrenheit F" else "Celsius C")
                }

                Button(onClick = {
                                 settingsViewModel.deleteAllUnits()
                                 settingsViewModel.insertUnit(Unit(choiceState.value))
                                 },
                modifier = Modifier
                    .padding(3.dp)
                    .align(CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xffefbe42)
                    )
                ) {

                    Text(text = "Save",
                    modifier = Modifier.padding(3.dp),
                    color = Color.White,
                    fontSize = 17.sp)

                }
            }
        }
        
    }
}