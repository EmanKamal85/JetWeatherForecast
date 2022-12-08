package com.example.jetweatherforcast.screens.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherforcast.model.Favorite
import com.example.jetweatherforcast.model.Unit
import com.example.jetweatherforcast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: WeatherDbRepository)
    :ViewModel(){

    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

        init {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getUnits().distinctUntilChanged().collect{listOfUnits->
                    if (listOfUnits.isNullOrEmpty()){
                        Log.d("ListOfUnits", "Empty Settings: Empty List Of Units ")
                        repository.insertUnit(Unit("celsius"))
                    }else{
                        _unitList.value = listOfUnits
                        Log.d("Units", ": $listOfUnits")
                    }
                }
            }
        }

    fun insertUnit(unit: Unit) = viewModelScope.launch {
        repository.insertUnit(unit)
    }

    fun updateUnit(unit: Unit) = viewModelScope.launch {
        repository.updateUnit(unit)
    }

    fun deleteAllUnits() = viewModelScope.launch {
        repository.deleteAllUnits()
    }

    fun deleteUnit(unit: Unit) = viewModelScope.launch {
        repository.deleteUnit(unit)
    }
}