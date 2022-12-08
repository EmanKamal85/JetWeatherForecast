package com.example.jetweatherforcast.screens.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherforcast.model.Favorite
import com.example.jetweatherforcast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository)
    :ViewModel(){

     private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavorites().distinctUntilChanged().collect { listOfFavs ->
                if (listOfFavs.isNullOrEmpty()) {
                    Log.d("Empty", ":Empty List ")
                } else{
                    _favList.value =listOfFavs
                    Log.d("Favs", ": $favList")
                }
            }
        }
    }

        fun getFavById(city : String) = viewModelScope.launch {
            repository.getFavById(city)
        }

        fun insertFavorite(favorite: Favorite) = viewModelScope.launch {
            repository.insertFavorite(favorite)
        }

        fun updateFavorite(favorite: Favorite) = viewModelScope.launch {
            repository.updateFavorite(favorite)
        }

        fun deleteAllFavorites() = viewModelScope.launch {
            repository.deleteAllFavorites()
        }

        fun deleteFavorite(favorite: Favorite) = viewModelScope.launch {
            repository.deleteFavorite(favorite)
        }

}