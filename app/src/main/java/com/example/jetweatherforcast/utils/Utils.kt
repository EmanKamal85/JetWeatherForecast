package com.example.jetweatherforcast.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate (timeStamp : Int) : String{
    val sdf = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
    val date = Date(timeStamp.toLong() * 1000)
    return sdf.format(date)
}

fun formatDateTime (timeStamp : Int) : String{
    val sdf = SimpleDateFormat("hh:mm:aa", Locale.getDefault())
    val date = Date(timeStamp.toLong() * 1000)
    return sdf.format(date)
}

fun formatDecimal(item: Double) : String{
    return "% .0f".format(item)
}

fun formatDayName (timeStamp : Int) : String{
    val sdf = SimpleDateFormat("EEE", Locale.getDefault())
    val date = Date(timeStamp.toLong() * 1000)
    return sdf.format(date)
}